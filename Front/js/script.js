function abrirMenu(b){
    var a = document.getElementById("menuDesplegable"+ b);
    if(a.style.display == "block"){
        a.style.display = "none";
    }else{
        a.style.display = "block";
    }
}
 function mensajeConfirmacion(id){
    var a = document.getElementById("open_PopUp");
    var button = document.getElementById("eliminar");
    button.onclick = function() {
        eliminarCategoria(id);
    };
    if(a.style.display == "flex"){
        a.style.display = "none";
    }else{
        a.style.display = "flex";
    }
 }
 /* Espera a que el documento esté cargado antes de ejecutar el código */
document.addEventListener('DOMContentLoaded', function() {
    getCategorias(); 
});

//GET ALL
function getCategorias() {
    // URL del endpoint
    const url = 'http://localhost:9000/categories';

    // Llamada a la API
    fetch(url)
        .then(function(response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Error en la llamada a la API: ' + response.statusText);
            }
        })
        .then(function(categories) { 
            console.log("Categorías recibidas:", categories); 
            renderCategory(categories);
        })
        .catch(function(error) {
            console.error('Error al obtener las categorías:', error);
        });
}

function showCategory(id) {
    localStorage.setItem('idCategoria', id);
    window.location.href = 'showCategory.html';
}

function renderCategory(categories) {
    var container = document.getElementById('categories-container');

    // Limpiar el contenedor antes de renderizar nuevas categorías
    container.innerHTML = '';

    // Recorrer las categorías y agregarlas al contenedor
    categories.forEach(function(category, index) {
        var categoryElement = document.createElement('div');
        categoryElement.classList.add('category');

        categoryElement.innerHTML = `
            <h3>${category.name}</h3>
            <button type="button" onclick="abrirMenu(${index})">
                <i class="bi bi-list"></i>
            </button>
            <ul id="menuDesplegable${index}" class="submenu">
                <li><a onclick='showCategory(${category.id})'>Mostrar información</a></li>
                <li><a href='modifyCategory.html'>Modificar categoría</a></li>
                <li>
                    <a type="button" href="#" onclick="mensajeConfirmacion(${category.id})">Eliminar categoría</a>
                </li> 
            </ul> 
        `;

        container.appendChild(categoryElement);
    });

    console.log("Categorías renderizadas correctamente.");
}

 
    //DELETE
    function eliminarCategoria(id) {
        // Asegúrate de tener la URL correcta para la API
        const apiUrl = `http://localhost:9000/categories/${id}`;
    
        fetch(apiUrl, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Error al eliminar la categoría');
        })
        .then(data => {
            alert('Categoría eliminada correctamente');
            getCategorias();
            document.getElementById('open_PopUp').style.display = 'none';  // Ocultamos el pop-up
        })
        .catch(error => {
            alert('Error al eliminar la categoría');
        });
    }

    //PUT
    function guardarNombre(){
    let newName = document.getElementById("newName").value;
    const apiUrl = `http://localhost:9000/categories/${id}`;
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

//GET ONE
document.addEventListener('DOMContentLoaded', function(){

    function getCategory(){
        id = localStorage.getItem('idCategoria');
        const apiUrl = `http://localhost:9000/categories/${id}`;

        fetch(apiUrl)
            .then(function(response){
                if(!response.ok){
                    throw new Error('Error en la respuesta de la API' + response.statusText);
                }else{
                    return response.json();
                }
            })
            .then(function(category){
                renderCategory(category);
            })
            .catch(function(error){
                console.error('Error al cargar la categoría ' + error);
                document.getElementById('card').innerHTML  = '<p>Error al cargar la categoría</p>';
            });
    }

    function renderCategory(category){

        
        if(!category){
            document.getElementById('name').innerHTML = 'No se encontró la categoría';
            document.getElementById('lista').innerHTML = '<li>No hay canciones disponibles</li>';
        }else{
            document.getElementById('name').innerText = category.name;
            if(!category.songs){
                document.getElementById('lista').innerHTML = '<li>No hay canciones disponibles</li>';
            }else{
                const listaElement = document.getElementById('lista');
                listaElement.innerHTML = '';
                category.songs.forEach(song => {
                    const li = document.createElement('li');
                    li.innerText = song.name;
                    listaElement.appendChild(li);
                });
             }
            
            

            
        }
    }


   

    getCategory();

}); 

 //POST
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
