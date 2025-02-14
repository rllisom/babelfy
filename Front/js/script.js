async function showCategory(){

    url = ""
    resultado = await GetapiRequest(url);

    
    let p = document.getElementById("name");

    p.textContent = resultado[0]["name"]
    
    for (let index = 0; index < resultado[0]["songs"].length; index++) {
        
        let ul = document.getElementById("lista");
        let li = document.createElement("li");
        li.textContent = resultado[0]["songs"][index]
        
    }





}