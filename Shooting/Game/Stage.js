class Stage {
    constructor(gw, gh, imgs){

        this.stage1 = [
        {
            time:2, 
            data:{
                x:gw / 3 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:2, 
            data:{
                x:gw / 3 * 2 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:6, 
            data:{
                x:-50, 
                y:0, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(1, 1),
                hp:5
            }
        },
        {
            time:6, 
            data:{
                x:gw, 
                y:0, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(-1, 1),
                hp:5
            }
        },//00000000000000000000000000000000
        {
            time:9, 
            data:{
                x:gw / 6 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:9, 
            data:{
                x:gw / 6 * 2 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:9, 
            data:{
                x:gw / 6 * 3 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:9, 
            data:{
                x:gw / 6 * 4 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:9, 
            data:{
                x:gw / 6 * 5 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },//0000000000000000000000000000000000000000
        {
            time:9, 
            data:{
                x:-50, 
                y:100,
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(1, 0),
                hp:5
            }
        },
        {
            time:9, 
            data:{
                x:gw, 
                y:100,
                w:60,
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(-1, 0),
                hp:5
            }
        },//000000000000000000000000000000000000000000
        {
            time:12, 
            data:{
                x:gw / 7 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:12.5, 
            data:{
                x:gw / 7 * 2 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:13, 
            data:{
                x:gw / 7 * 3 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:13.5, 
            data:{
                x:gw / 7 * 4 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:14, 
            data:{
                x:gw / 7 * 5 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },
        {
            time:14.5, 
            data:{
                x:gw / 7 * 6 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy, 
                s:100, 
                v:new Vector(0, 1),
                hp:5
            }
        },//0000000000000000000000000000000000000000
        {
            time:18, 
            data:{
                x:gw / 10 * 1 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:18, 
            data:{
                x:gw / 10 * 2 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:18, 
            data:{
                x:gw / 10 * 3 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:18, 
            data:{
                x:gw / 10 * 4 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:18, 
            data:{
                x:gw / 10 * 5 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:18, 
            data:{
                x:gw / 10 * 6 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:18, 
            data:{
                x:gw / 10 * 7 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:18, 
            data:{
                x:gw / 10 * 8 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:18, 
            data:{
                x:gw / 10 * 9 - 30, 
                y: -40, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, 1),
                bs:20,
                hp:5
            }
        },
        {
            time:22, 
            data:{
                x:gw / 10 * 1 - 30, 
                y: 840, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, -1),
                bs:20,
                hp:5
            }
        },
        {
            time:22, 
            data:{
                x:gw / 10 * 2 - 30, 
                y: 840, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, -1),
                bs:20,
                hp:5
            }
        },
        {
            time:22, 
            data:{
                x:gw / 10 * 3 - 30, 
                y: 840, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, -1),
                bs:20,
                hp:5
            }
        },
        {
            time:22, 
            data:{
                x:gw / 10 * 4 - 30, 
                y: 840, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, -1),
                bs:20,
                hp:5
            }
        },
        {
            time:22, 
            data:{
                x:gw / 10 * 5 - 30, 
                y: 840, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, -1),
                bs:20,
                hp:5
            }
        },
        {
            time:22, 
            data:{
                x:gw / 10 * 6 - 30, 
                y: 840, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, -1),
                bs:20,
                hp:5
            }
        },
        {
            time:22, 
            data:{
                x:gw / 10 * 7 - 30, 
                y: 840, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, -1),
                bs:20,
                hp:5
            }
        },
        {
            time:22, 
            data:{
                x:gw / 10 * 8 - 30, 
                y: 840, 
                w:60, 
                h:40, 
                img:imgs.enemy2, 
                s:500, 
                v:new Vector(0, -1),
                bs:20,
                hp:5
            }
        },
        {
            time:25, 
            data:{
                x:gw / 10 * 6 - 50, 
                y: -80, 
                w:100, 
                h:80, 
                img:imgs.enemy3, 
                s:18, 
                v:new Vector(0, 1),
                bs:20,
                hp:40
            }
        },
        {
            time:25, 
            data:{
                x:gw / 10 * 4 - 50, 
                y: -80, 
                w:100, 
                h:80, 
                img:imgs.enemy3, 
                s:18, 
                v:new Vector(0, 1),
                bs:20,
                hp:40
            }
        },
        {
            time:29, 
            data:{
                x:gw / 10 * 8 - 50, 
                y: -80, 
                w:100, 
                h:80, 
                img:imgs.enemy3, 
                s:18, 
                v:new Vector(0, 1),
                bs:20,
                hp:40
            }
        },
        {
            time:29, 
            data:{
                x:gw / 10 * 2 - 50, 
                y: -80, 
                w:100, 
                h:80, 
                img:imgs.enemy3, 
                s:18, 
                v:new Vector(0, 1),
                bs:20,
                hp:40
            }
        },
        {
            time:33, 
            data:{
                x:gw / 2 - 150, 
                y: -200, 
                w:300, 
                h:200, 
                img:imgs.boss, 
                s:50, 
                v:new Vector(0, 1),
                hp:100,
                mop:3
            }
        },
        ];
    }
}