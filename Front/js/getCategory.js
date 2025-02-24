document.addEventListener('DOMContentLoaded', function() {
    getCategory();
});
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
        .then()
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
        if (!category.songsDTO || category.songsDTO.length === 0) {
            listElement.innerHTML = '<li>No hay canciones disponibles</li>';
        } else { 
            listElement.innerHTML = '';
            category.songsDTO.forEach(song => {
                const li = document.createElement('li');
                
                listElement.appendChild(li);
            });
        }
    }
    return true;
}
function getSongById_2(){
            
    const id = localStorage.getItem('idSong');
    const apiUrl = `http://localhost:9000/songs/`+id;

    fetch(apiUrl)
        .then(function(response){
            return response.json();
        })
        .then(function(song){
            renderSingleSong(song);
        })
        .catch(function(error){
            console.error('Error al cargar la canción: ' + error);
           
        });

}




async function renderSingleSong_2(song) {
    
    const idCategoria = localStorage.getItem('idCategoria');
    try {
        console.log("Canción obtenida:", song);

        const categoryResponse = await fetch(`http://localhost:9000/categories/`+idCategoria);
        if (!categoryResponse.ok) throw new Error("Error al obtener la categoría");
            

        document.getElementById('songName').innerText = song.name;
     
    } catch (error) {
        console.error(' Error al renderizar la canción:', error);
    }
    
}
