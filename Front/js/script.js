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

 document.addEventListener('DOMContentLoaded', function() {
    if (document.getElementById('name') && document.getElementById('lista')) {
        getCategory(); 
    } else if (document.getElementById('categories-container')) {
        getCategorias();
    }
});

//GET ALL
function getCategorias() {
    
    const url = 'http://localhost:9000/categories';

    
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
            console.error('Error al obtener las categorías:' + error);
        });
}

function showCategory(id) {
    localStorage.setItem('idCategoria', id);
    window.location.href = 'showCategory.html';
}

function modifyCategory(id) {
    localStorage.setItem('idCategoria', id);
    window.location.href = 'modifyCategory.html';
}

function renderCategory(categories) {
    var container = document.getElementById('categories-container');

    container.innerHTML = '';

    categories.forEach(function(category, index) {
        var categoryElement = document.createElement('div');
        categoryElement.classList.add('category');

        categoryElement.innerHTML = 
        ` <h3>${category.name}</h3>
            <button type="button" onclick="abrirMenu(${index})">
                <i class="bi bi-list"></i>
            </button>
            <ul id="menuDesplegable${index}" class="submenu">
                <li><a onclick='showCategory(${category.id})'>Mostrar información</a></li>
                <li><a onclick = 'modifyCategory(${category.id})' >Modificar categoría</a></li>
                <li>
                    <a type="button" href="#" onclick="mensajeConfirmacion(${category.id})">Eliminar categoría</a>
                </li> 
            </ul>  `;

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
    const id = localStorage.getItem('idCategoria');
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
            if (response.ok) {
                return response.text(); // Intentamos obtener texto en lugar de JSON
            } else {
                throw new Error('Error al actualizar el nombre');
            }
        })
        .then(text => {
            if (!text) {
                // Si el backend devuelve `null` (que se traduce en una cadena vacía), mostramos el mensaje
                alert('Fallo al actualizar el nombre. Ya existe');
                return;
            }
            return JSON.parse(text); // Convertimos solo si hay contenido JSON
        })
        .then(data => {
            if (data) {
                alert('Categoría actualizada correctamente');
                window.location.href = 'category.html';
            }
        })
        .catch(error => {
            console.log(error);
            
            alert('Error al actualizar el nombre');
        });
        
    }

    
}

//GET ONE
// document.addEventListener('DOMContentLoaded', function(){

//     function getCategory(){
//         const id = localStorage.getItem('idCategoria');
//         const apiUrl = `http://localhost:9000/categories/${id}`;

//         fetch(apiUrl)
//             .then(function(response){
//                 if(!response.ok){
//                     throw new Error('Error en la respuesta de la API' + response.statusText);
//                 }else{
//                     return response.json();
//                 }
//             })
//             .then(function(category){
//                 renderCategory(category);
//             })
//             .catch(function(error){
//                 console.error('Error al cargar la categoría ' + error);
//                 document.getElementById('card').innerHTML  = '<p>Error al cargar la categoría</p>';
//             });
//     }

//     function renderCategory(category){

//         const nameElement = document.getElementById('name');
//         const listElement = document.getElementById('lista');

//         if(!nameElement || !listElement){
//             console.error('No se encontraron los elementos');
//             return;
//         }

//         if(!category){
//             nameElement.innerHTML = 'No se encontró la categoría';
//             listElement.innerHTML = '<li>No hay canciones disponibles</li>';
//         }else{
//             nameElement.innerText = category.name;
//             if(!category.songs){
//                 listElement.innerHTML = '<li>No hay canciones disponibles</li>';
//             }else{
//                 listElement.innerHTML = '';
//                 category.songs.forEach(song => {
//                     const li = document.createElement('li');
//                     li.innerText = song.name;
//                     listElement.appendChild(li);
//                 });
//              }
            
            

            
//         }
//     }
//     getCategory();

// }); 

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
                return response.text();
            }
            throw new Error('Error al crear la categoría');
        })
        .then(text => {
            if(!text){
                alert('Fallo al crear la categoría. Ya existe');
                return;
            }
            return JSON.parse(text);
        })
        .then(data => {
            if(data){
            alert('Categoría creada correctamente');
            window.location.href = 'category.html';
            }
        })
        .catch(error => {
            alert('Error al crear la categoría');
        });
    }
}
