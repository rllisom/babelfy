
function getSongById(){
    const id = localStorage.getItem('idCancion');
    const apiUrl = `http://localhost:9000/songs/${id}`;

    fetçh(apiUrl)
        .then(function(response){
            return response.json();
        })
        .then(function(song){
            renderSingleSong(song);
        })
        .catch(function(error){
            console.error('Error al cargar la canción: ' + error);
            const card = document.getElementById('card');
            if(card){
                card.innerHTML = '<p>Error al cargar la canción</p>';
                }
        });

}

function renderSingleSong(song){
    const nameElement = document.getElementById('songName');
    const authorElement = document.getElementById('author');
    const albumElement = document.getElementById('album');
    const categoryElement = document.getElementById('category');
    const dateElement = document.getElementById('releaseDate');
    const durationElement = document.getElementById('duration');

    if(!nameElement || !authorElement || !albumElement || !categoryElement || !dateElement || !durationElement){
        console.error('No se encontraron los elementos "songName", "author", "album", "category", "releaseDate" o "duration" en el DOM.');
        return;
    }

    if(!song){
        nameElement.innerHTML = 'No se encontró la canción';
        
    } else {
        nameElement.innerText = song.name;
        authorElement.innerText = song.author;
        albumElement.innerText = song.album;
        categoryElement.innerText = song.category;
        dateElement.innerText = song.releaseDate;
        durationElement.innerText = song.duration;
    }
}
