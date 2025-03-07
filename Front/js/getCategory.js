document.addEventListener('DOMContentLoaded', function() {
    getCategory();
});

// Función para obtener y renderizar una categoría con sus canciones
function getCategory() {
    const idCategoria = localStorage.getItem('idCategoria'); // Obtenemos el ID de la categoría


    const apiUrl = `http://localhost:9000/categories/${idCategoria}`;

    fetch(apiUrl)
        .then(function(response) {
            if (!response.ok) {
                throw new Error(' Error en la respuesta de la API: ' + response.statusText);
            }
            return response.json();
        })
        .then(function(category) {
            renderSingleCategory(category);
        })
        .catch(function(error) {
            console.error(' Error al cargar la categoría:', error);
            const card = document.getElementById('card');
            if (card) {
                card.innerHTML = '<p>Error al cargar la categoría</p>';
            }
        });
}

function renderSingleCategory(category) {
    const nameElement = document.getElementById('name');
    const listElement = document.getElementById('lista');

    nameElement.innerText = category.name;

    let songs = category.songsDTO || category.songs; // Intentar ambas opciones

    if (!songs || songs.length === 0) {
        listElement.innerHTML = '<li>No hay canciones disponibles</li>';
        return;
    }

    listElement.innerHTML = ''; // Limpiar la lista antes de agregar nuevas canciones

    songs.forEach(song => {

        const li = document.createElement('li');
        li.textContent = song.name;
        li.onclick = function() {
            localStorage.setItem('idSong', song.id);
            window.location.href = 'showSong.html';
        };
        listElement.appendChild(li);
    });
}
