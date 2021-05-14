window.addEventListener("load", ()=>{
    $.getJSON("resources/store.json", (e)=> {
            let app = new App(e);
        }
    );
});

class App{
    constructor(list){
        this.list = list;
        this.jlist();
        this.main();
    }

    jlist(){
        this.list.forEach(e => {
            let product = this.form(e);
            document.getElementById("items").appendChild(product);
        });
    }

    form(e){
        let div = document.createElement("div");
        div.classList.add("item");
        div.dataset.id = e.id;
        div.style.backgroundColor = "#fff";
        div.innerHTML = `<div class="brand">${e.brand}</div>
        <div class="img">
            <img src="Design/sources/${e.photo}" alt="poto">
        </div>
        <div class="name">${e.product_name}</div>
        <div class="price">${e.price}</div>`;
        return div;
    }
    main() {
        $(".item").draggable({
            revert : true,
            zIndex : 2,
            cancel : ".infoBox",
            containment : "html",
            helper: "clone",
            drag() {

            },
            stop() {

            }
        });

        // $(".drop").droppable({
        //     accept: ".item",
        //     drop(event, ui) {
        //         let id = ui.draggable[0].dataset.id;
        //         console.log(id);
        //     },
        // });
    }
}