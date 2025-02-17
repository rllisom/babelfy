async function modifyCategory(){

    url = "";
    let respuesta = PUTapiRequest(url);


    let input = document.createElement("input")
    input.classList.add("dataAPI")


}


function guardarNombre(){
    let name = document.getElementById("newName").value;

    return name;
}