$(function(){
    //여기는 윈도우 로드가 전부 완료되었을 때 실행됩니다.

    $("section").on("mousemove", function(e){
        let posX = e.pageX;
        let posY = e.pageY;
        //첫번째 섹션
        $(".p11").css({"right":20 - (posX / 30), "bottom":20 - (posY / 30)});
        $(".p12").css({"right":130 - (posX / 20), "bottom":-40 - (posY / 20)});
        $(".p13").css({"right":60 - (posX / 20), "top":180 - (posY / 20)});

        //두번째 섹션
        $(".p21").css({"right":-180 - (posX / 30), "bottom":-480 - (posY / 30)});
        $(".p22").css({"right":130 + (posX / 50), "bottom":-40 - (posY / 50)});
        
        //세번째 섹션
        $(".p31").css({"right":280-(posX/30), "bottom":30-(posY/30)}); 
        $(".p32").css({"right":110+(posX/20), "bottom":-270+(posY/20)}); 
        $(".p33").css({"right":-70+(posX/20), "bottom":-130+(posY/20)});
        
        //네번째 섹션
        $(".p41").css({"right":20-(posX/30), "bottom":-120-(posY/30)});
        $(".p42").css({"right":0+(posX/30), "bottom":-180+(posY/20)});
    });

    $("#menu li").on("click", function(e){
        e.preventDefault();

        let wh = $(window).height(); //현재 윈도우의 높이(크기)
        // let idx = $(this).index();
        let idx = $(this).data("idx");

        let scrollValue = idx * wh;
        
        $("html, body").stop().animate({"scrollTop": scrollValue}, 1000);
    });

    $(window).on("scroll", function(e){
        let st = $(window).scrollTop();
        let wh = $(window).height();

        $("#menu li").removeClass("on");
        if ( st >= 0 && st < wh) {
            //첫번째 섹션에 마우스가 가있다.
            $("#menu li").eq(0).addClass("on");
        }else if ( st < 2 * wh) {
            //두번째 섹션에 있다.
            $("#menu li").eq(1).addClass("on");
        }else if ( st < 3 * wh){
            $("#menu li").eq(2).addClass("on");
        }else {
            $("#menu li").eq(3).addClass("on");
        }
    });

    let scrolling = false;

    $(window).on("wheel", function(e){
        if(scrolling) return;
        scrolling = true;
        let st = $(window).scrollTop();
        let wh = $(window).height();

        let target = 0;

        if(e.originalEvent.deltaY < 0 ) {
            //올라간다.
            if ( st < 2 * wh) {
                target=0;
            }else if ( st < 3 * wh) {
                target=wh;
            }else if ( st < 4 * wh){
                target=2*wh;
            }
        }else {
            //내려간다
            if ( st >= 0 && st < wh) {
                target=wh;
            }else if ( st < 2 * wh) {
                target=2*wh;
            }else if ( st < 3 * wh){
                target=3*wh;
            }
        }
        $("html, body").stop().animate({"scrollTop":target}, 1500, "easeOutBounce", scrollComplete());
    });

    function scrollComplete(){
        scrolling = false;
    }
});