/* Espera a que el documento esté cargado antes de ejecutar el código */
document.addEventListener('DOMContentLoaded', function() {
    getCategorias(); 
});

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
                <li><a href="showCategory.html">Mostrar información</a></li>
                <li><a href="modifyCategory.html">Modificar categoría</a></li>
                <li>
                    <a type="button" href="#" onclick="mensajeConfirmacion(${category.id})">Eliminar categoría</a>
                </li> 
            </ul> 
        `;

        container.appendChild(categoryElement);
    });

    console.log("Categorías renderizadas correctamente.");
}

 
    
    function eliminarCategoria(id) {
        // Asegúrate de tener la URL correcta para la API
        const apiUrl = `https://miapi.com/categorias/${id}`;
    
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
            // Aquí podrías actualizar la lista de categorías o hacer otras acciones necesarias
            document.getElementById('open_PopUp').style.display = 'none';  // Ocultamos el pop-up
        })
        .catch(error => {
            alert('Error al eliminar la categoría');
        });
    }
    
