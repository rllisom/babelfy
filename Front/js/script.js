function abrirMenu(b){
    var a = document.getElementById("menuDesplegable"+ b);
    if(a.style.display == "block"){
        a.style.display = "none";
    }else{
        a.style.display = "block";
    }
}