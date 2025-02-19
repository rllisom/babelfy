
// Función para obtener y renderizar una única categoría
function getCategory() {
    const id = localStorage.getItem('idCategoria');
    const apiUrl = `http://localhost:9000/categories/${id}`;

    fetch(apiUrl)
        .then(function(response) {
            if (!response.ok) {
                throw new Error('Error en la respuesta de la API: ' + response.statusText);
            }
            return response.json();
        })
        .then(function(category) {
            renderSingleCategory(category);
        })
        .catch(function(error) {
            console.error('Error al cargar la categoría: ' + error);
            const card = document.getElementById('card');
            if (card) {
                card.innerHTML = '<p>Error al cargar la categoría</p>';
            }
        });
}

// Función para renderizar la categoría en el DOM
function renderSingleCategory(category) {
    const nameElement = document.getElementById('name');
    const listElement = document.getElementById('lista');

    if (!nameElement || !listElement) {
        console.error('No se encontraron los elementos "name" o "lista" en el DOM.');
        return;
    }

    if (!category) {
        nameElement.innerHTML = 'No se encontró la categoría';
        listElement.innerHTML = '<li>No hay canciones disponibles</li>';
    } else {
        nameElement.innerText = category.name;
        if (!category.songs || category.songs.length === 0) {
            listElement.innerHTML = '<li>No hay canciones disponibles</li>';
        } else {
            listElement.innerHTML = '';
            category.songs.forEach(song => {
                const li = document.createElement('li');
                li.innerText = song.name;
                listElement.appendChild(li);
            });
        }
    }
}
