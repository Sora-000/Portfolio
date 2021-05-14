let log = console.log;
class App {
    constructor(){
        App.app = this; // 앱에 스태틱으로 넣었다 이말이야
        this.gameWidth = 700;
        this.gameHeight = 900;

        this.canvas = document.querySelector("#myGame");
        this.ctx = this.canvas.getContext("2d");
        this.start = false;
        this.imageList = {}; //이미지 저장 오브젝트

        this.player = null;
        this.backList = []; //배경그림 리스트
        this.playerBulletList = []; //플레이어 총알 리스트

        this.enemyList = []; //적기체 저장 리스트
        this.expList = []; //폭발리스트

        //여기에 스테이지 데이터 제어 변수들이 들어갑니다.
        this.gameTimer = 0; //게임이 시작되고 몇초가 흘렀는지 저장
        this.stageIdx = 0; //지금 몇번째 적을 만들어내는지 저장

        this.stageData = []; //스테이지의 데이터

        this.over = false;

        this.init(); //초기화 함수
        
        let yydh = 0;
        document.addEventListener("keypress", (e) => {
            log(yydh);
            if (e.key == "y" && yydh == 0) {yydh = 1;}
            else if (e.key == "y" && yydh == 1) {yydh = 2;}
            else if (e.key == "d" && yydh == 2) {yydh = 3;}
            else if (e.key == "h" && yydh == 3) {this.start = true;yydh = 0;}
            else yydh = 0; 
                // document.querySelector("#startBtn").click();
            
        });

        
    }

    async init(){
        this.imageList.player = await this.loadImage("player.png");
        this.imageList.back = await this.loadImage("back.png");
        this.imageList.enemy = await this.loadImage("enemy1.png");
        this.imageList.enemy2 = await this.loadImage("enemy2.png");
        this.imageList.enemy3 = await this.loadImage("enemy3.png");
        this.imageList.explosion = await this.loadImage("explosion.png");
        this.imageList.boss = await this.loadImage("boss.png");
        
        //백그라운드 생성
        for(let i = 0; i < 3; i++){
            this.backList.push(
                new Background(0, - i * this.gameHeight, 
                    this.gameWidth, this.gameHeight,
                    this.imageList.back));
        }
        //플레이어 생성(x좌표 y좌표 너비 높이 이미지)
        this.player = new Player(
            this.gameWidth / 2 - 30, this.gameHeight - 60,
            60, 40, this.imageList.player, this);

        let stage = new Stage(this.gameWidth, this.gameHeight, this.imageList);
        this.stageData = stage.stage1;
        
        // let tempExp = new Explosion(100, 100, 60, 60, this.imageList.explosion);
        // this.expList.push(tempExp);

        requestAnimationFrame(this.frame.bind(this));
    }

    getOrCreateExplosion(x, y, w, h){
        let exp = this.expList.find(x => !x.active);
        if(exp === undefined){
            exp = new Explosion(this.imageList.explosion);
            this.expList.push(exp);
        }
        exp.setActive(x, y, w, h);
    }

    getOrCreateBullet(x, y, r, s = 5, v, isEnemy = true){
        let bullet = this.playerBulletList.find(x=> !x.active);
        if(bullet == undefined) {
            bullet = new Bullet();    
            this.playerBulletList.push(bullet);
        }
        bullet.setActive(x,y,r,s,v, isEnemy);
    }

    getOrCreateEnemy(data){
        let e = this.enemyList.find(x => !x.active);
        if(e === undefined){
            e = new Enemy();
            this.enemyList.push(e);
        }
        e.reset(data.x, data.y, data.w, data.h, data.img, data.s, data.v, data.bs, data.hp, data.mop);
    }

    loadImage(name){
        return new Promise((res, rej)=>{
            let img = new Image();
            img.src = `./images/${name}`;
            img.addEventListener("load", ()=>{
                res(img);
            });
        });
    }

    frame(time){
        if(!this.start) this.start = time;
        let delta = time - this.start;
        this.start = time;
        if(!this.over){
            this.update(delta / 1000);
            this.render();
        }
        requestAnimationFrame(this.frame.bind(this));
    }
    gameOver(){
        this.over = true;
        log($("#asdf"));
        $("#asdf").fadeIn();
        console.log("게임오버");
    }


    update(delta){
        this.gameTimer += delta; //이렇게 되면 게임 진행시간이 this.gameTimer에 들어간다.
        this.backList.forEach(back => back.update(delta));
        if(this.backList[0].y > this.gameHeight){
            let first = this.backList.shift();
            first.y = this.backList[this.backList.length - 1].y - this.gameHeight;
            this.backList.push(first);
        }
        this.player.update(delta);
        this.player.checkOut(this.gameWidth, this.gameHeight);

        let nowEnemy = this.stageData[this.stageIdx];
        if(nowEnemy !== undefined && nowEnemy.time <= this.gameTimer){
            this.getOrCreateEnemy(nowEnemy.data);
            this.stageIdx++;
        }

        
        document.getElementById("time").innerHTML = "Time : "+Math.floor(this.gameTimer);

        this.playerBulletList.forEach(b => b.update(delta));
        this.enemyList.forEach(e => e.update(delta));

        this.playerBulletList.filter(b => b.active).forEach(b => {
            if(!b.isEnemy){
                this.enemyList.filter(e => e.active).forEach(e => {
                    if(e.checkCollision(b.x, b.y, b.r)){
                        e.setDamage(b.damage);
                        b.active = false;
                    }
                });
            }else {
                //적총알이 플레이어에 충돌했는지를 검사
                if(this.player.checkCollision(b.x,b.y,b.r)){
                    this.player.setDamage(b.damage);
                    this.player.active = false;
                    b.active = false;
                }

            }
        });

        this.expList.forEach(e => e.update(delta));
    }

    render(){
        this.ctx.clearRect(0,0,this.gameWidth, this.gameHeight);
        this.backList.forEach(back => back.render(this.ctx));
        this.playerBulletList.forEach(b => b.render(this.ctx));
        this.enemyList.forEach(e => e.render(this.ctx));
        this.player.render(this.ctx);
        this.expList.forEach(e => e.render(this.ctx));
    }

}