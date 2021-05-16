class Enemy{
    constructor(){
        this.x = null;
        this.y = null;
        this.w = null;
        this.h = null;
        this.img = null;
        this.vector = null;
        this.bulletspeed = null
        this.speed = null;
        this.active = false;
        this.hp = null;
        this.mop = null;
        this.phase = false;

        this.enemyfireTerm = 900;
        this.bossfireTerm = 500;
    }

    reset (x, y, w, h, img, s, v, bs = 5, hp, mop = 0){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = img;
        this.speed = s;
        this.vector = v;
        this.bulletspeed = bs;
        this.active = true;
        this.hp = hp;
        this.hpMax = hp;
        this.mop = mop;

        if(this.mop==3){
            this.bossfire();
        }else{
            this.fire();
        }
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
        
        
        if(this.mop == 3){
            let sss = document.getElementById("asdf");
            sss.innerHTML ="Game<br>CLEAR";
            sss.style.color = "#2ff5eb";
            $("#asdf").fadeIn();
        }
        this.active = false;
    }

    bossfire(){
        if(!this.active) return;
        if(this.hp > 60){
            for(let i = 1; i < 12; i++){
                App.app.getOrCreateBullet(this.x + this.w / 12 * i      , this.y + this.h - 5 , 3, 300, new Vector((-6+i), 9));
                App.app.getOrCreateBullet(this.x + this.w / 12 * (i-1)  , this.y + this.h - 5 , 3, 300, new Vector((-4+i), 9));
            }
        }else if (this.hp > 40){
        this.bossfireTerm = 1000;
            for(let i = 1; i < 12; i++){
                App.app.getOrCreateBullet(this.x + this.w / 4 * 2 , this.y + this.h - 100 , 3, 300, new Vector((-6+i), 0));
                App.app.getOrCreateBullet(this.x + this.w / 4 * 2 , this.y + this.h - 50 , 3, 300, new Vector((-6+i), 0));
                // App.app.getOrCreateBullet(this.x + this.w / 4 * 2 , this.y + this.h - 50 , 3, 300, new Vector((-6+i), 8));
            }
            for(let i = 1; i < 10; i++){
                App.app.getOrCreateBullet(this.x + this.w / 4     , this.y + this.h - 150 , 3, 300, new Vector((-5+i), 8));
                App.app.getOrCreateBullet(this.x + this.w / 4 * 3 , this.y + this.h - 150 , 3, 300, new Vector((-5+i), 8));
                App.app.getOrCreateBullet(this.x + this.w / 4     , this.y + this.h - 150 , 3, 300, new Vector((-5+i), 0));
                App.app.getOrCreateBullet(this.x + this.w / 4 * 3 , this.y + this.h - 150 , 3, 300, new Vector((-5+i), 0));
            }

        }else{
            this.bossfireTerm = 400;
            
            for(let i = 1; i < 12; i++){
                App.app.getOrCreateBullet(this.x + this.w / 4 * 2 , this.y + this.h - 100 , 3, 300, new Vector((-6+i), 0));
                App.app.getOrCreateBullet(this.x + this.w / 4 * 2 , this.y + this.h - 50 , 3, 300, new Vector((-6+i), 0));
            }
            for(let i = 1; i < 10; i++){
                App.app.getOrCreateBullet(this.x + this.w / 4     , this.y + this.h - 150 , 3, 300, new Vector((-5+i), 0));
                App.app.getOrCreateBullet(this.x + this.w / 4 * 3 , this.y + this.h - 150 , 3, 300, new Vector((-5+i), 0));
            }
            if(this.phase){
                for(let i = 1; i < 16; i++) App.app.getOrCreateBullet(this.x + this.w / 4 * 2 , this.y + this.h - 180 , 3, 300, new Vector((-8+i), 8));
                this.phase = false;
            }else{
                for(let i = 1; i < 9; i++) App.app.getOrCreateBullet(this.x + this.w / 4 * 2 , this.y + this.h - 180 , 3, 300, new Vector(-9+(i*2), 16));
                this.phase = true;
            }
        }

            setTimeout(this.bossfire.bind(this), this.bossfireTerm);  
        }
        fire(){
            if(!this.active) return;

            App.app.getOrCreateBullet(this.x + this.w / 2, this.y + this.h - 5 , 3, 300, new Vector(0, 1));
            setTimeout(this.fire.bind(this), this.enemyfireTerm);
        }

        update(d){
            if(!this.active) return;
            if(!this.mop){
            let normal = this.vector.normalize();
            this.x += normal.x * d * this.speed;
            this.y += normal.y * d * this.speed;

            if(this.x < -this.w * 2 || this.y < - this.h * 2 
                || this.x > this.w + App.app.gameWidth 
                || this.y > this.h + App.app.gameHeight
                )
            {
                this.active = false;
            }
        }else {
             let normal = this.vector.normalize();
             if(this.y >= 0)return;
            this.x += normal.x * d * this.speed;
            this.y += normal.y * d * this.speed;



            if(this.x < -this.w * 2 || this.y < - this.h * 2 
                || this.x > this.w + App.app.gameWidth 
                || this.y > this.h + App.app.gameHeight
                )
            {
                this.active = false;
            }
        }
        }

        checkCollision(x,y,r){
            let centerX = this.x + this.w / 2;
            let centerY = this.y + this.h / 2;

            let d = Math.pow(centerX - x, 2) + Math.pow(centerY - y,2);

            return d < Math.pow(r + Math.min(this.w, this.h) / 2, 2);
        }

        render(ctx){
            if(!this.active) return;
            ctx.drawImage(this.img, this.x, this.y, this.w, this.h);

            if(this.mop == 3){
                if(this.hp < this.hpMax/5) ctx.fillStyle = "#ff1d19";else if(this.hp < this.hpMax/2) ctx.fillStyle = "#fffc33";else ctx.fillStyle = "#3af05b";
        
               ctx.strokeStyle = "#cfcfcf";
            let percent = this.hp/this.hpMax;
            // ctx.strokeRect(this.x,this.y,this.w,10);
            // ctx.fillRect(this.x,this.y,this.w*percent,10);
            
            ctx.strokeRect(0,0,App.app.gameWidth,10);
            ctx.fillRect(0,0,App.app.gameWidth*percent,10);
        }
    }
}