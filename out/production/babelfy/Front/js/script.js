function abrirMenu(b){
    var a = document.getElementById("menuDesplegable"+ b);
    if(a.style.display == "block"){
        a.style.display = "none";
    }else{
        a.style.display = "block";
    }
}
 function mensajeConfirmacion(){
    var a = document.getElementById("open_PopUp");
    if(a.style.display == "flex"){
        a.style.display = "none";
    }else{
        a.style.display = "flex";
    }
 }