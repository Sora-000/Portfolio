class Player {
    constructor(x, y, w, h, img,app,hp = 5){
        this.app = app;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = img;
        this.keyArr = [];
        this.speed = 200;
        this.hpMax = hp;
        this.fireTerm = 0.3;        
        this.hp = hp;
        this.firing  = false;

        this.currentFireTerm = 0;
        this.init();        
    }
    
    setDamage(value){
        this.hp -= value;
        if(this.hp <= 0){
            this.explosion();
        }
    }

    explosion(){
        //폭발이펙트 생성
        App.app.getOrCreateExplosion(this.x,this.y, this.w, this.w);
        
        
        App.app.gameOver();
        this.active = false;
    }
    checkCollision(x,y,r){
        let centerX = this.x + this.w / 2;
        let centerY = this.y + this.h / 2;

        let d = Math.pow(centerX - x, 2) + Math.pow(centerY - y,2);
        
        return d < Math.pow(r + Math.min(this.w, this.h) / 2, 2);
    }

    init(){
        document.addEventListener("keydown", e => {
            if(e.code === "ArrowLeft")  this.keyArr[0] = true;
            if(e.code === "ArrowRight") this.keyArr[1] = true;
            if(e.code === "ArrowUp")    this.keyArr[2] = true;
            if(e.code === "ArrowDown")  this.keyArr[3] = true;
            if(e.code === "Space")      this.firing = true;
        });

        document.addEventListener("keyup", e => {
            if(e.code === "ArrowLeft")  this.keyArr[0] = false;
            if(e.code === "ArrowRight") this.keyArr[1] = false;
            if(e.code === "ArrowUp")    this.keyArr[2] = false;
            if(e.code === "ArrowDown")  this.keyArr[3] = false;
            if(e.code === "Space")      this.firing = false;
        })
    }

    fire(){
        if(this.currentFireTerm > 0 ) return;
        this.app.getOrCreateBullet(this.x+this.w/3, this.y , 3 , 300, new Vector(0,-1), false);
        this.app.getOrCreateBullet(this.x+this.w/3*2, this.y , 3 , 300, new Vector(0,-1), false);
        this.currentFireTerm = this.fireTerm;
    }

    update(d){
        if(this.currentFireTerm > 0) this.currentFireTerm -= d;

        let dx = 0, dy = 0;
        if(this.keyArr[0])  dx = -1;
        if(this.keyArr[1])  dx = 1;
        if(this.keyArr[2])  dy = -1;
        if(this.keyArr[3])  dy = 1;
        if(this.firing) this.fire();
        this.x += dx * d * this.speed;
        this.y += dy * d * this.speed;
    }

    checkOut(w, h){        
        if(this.x < 0 )             this.x = 0;
        if(this.x + this.w >= w)    this.x = w - this.w;
        if(this.y < 0)              this.y = 0;
        if(this.y + this.h >= h)    this.y = h - this.h;
    }

    render(ctx){

        ctx.drawImage(this.img, this.x, this.y, this.w, this.h);

        // ctx.strokeStyle = "#29fffb";
        // let percent = this.hp/this.hpMax;
        // ctx.fillStyle = "#29fffb";
        // ctx.strokeRect(0,0,App.app.gameWidth,10);
        // ctx.fillRect(0,0,App.app.gameWidth*percent,10);
        if(this.hp < this.hpMax/4) ctx.fillStyle = "#ff1d19";else if(this.hp < this.hpMax/2) ctx.fillStyle = "#fffc33";else ctx.fillStyle = "#3af05b";
        
        ctx.strokeStyle = "#cfcfcf";
        let percent = this.hp/this.hpMax;
        ctx.strokeRect(this.x,this.y+35,this.w,10);
        ctx.fillRect(this.x,this.y+35,(this.w)*percent,10);
    }
}