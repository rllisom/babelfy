
function guardarNombre(){
    
    let newName = document.getElementById("newName").value;
    const apiUrl = `http://localhost:9000/categories/1`;
    const message = document.getElementById("message");

    if(!newName.trim()){
        message.innerHTML = "Por favor, ingrese un nombre";
        return;
    }else{
        fetch(apiUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: newName})
        })
        .then(response => {
            if(response.ok){
                return response.json();
            }
            throw new Error('Error al actualizar el nombre');
        })
        .then(data => {
            alert('Categoría actualizada correctamente');
        })
        .catch(error => {
            alert('Error al actualizar el nombre');
        });
        
    }
}