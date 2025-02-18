function createCategory(){
    const apiUrl = `http://localhost:9000/categories`;
    let newName = document.getElementById("newName").value;
    const message = document.getElementById("message");


    if(!newName.trim()){
        message.innerHTML = "Por favor, ingrese un nombre";
        return;
    }else{
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: newName})
        })
        .then(response => {
            if(response.ok){
                return response.json();
            }
            throw new Error('Error al crear la categoría');
        })
        .then(data => {
            alert('Categoría creada correctamente');
        })
        .catch(error => {
            alert('Error al crear la categoría');
        });
    }
}