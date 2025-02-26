document.addEventListener('DOMContentLoaded', function() {
   getSongById();
});

function getSongById() {
    const id = localStorage.getItem('idSong');
  

    const apiUrl = `http://localhost:9000/songs/${id}`;

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) throw new Error("Error al obtener la canción");
            return response.json();
        })
        .then(song => {
            if (!song) throw new Error("La canción no fue encontrada");

    
            localStorage.setItem('songData', JSON.stringify(song));
            renderSingleSong(song);
        })
        .catch(error => console.error("Error obteniendo la canción:", error));
}

function renderSingleSong(song) {
   
    const storedSong = localStorage.getItem('songData');
        
    song = JSON.parse(storedSong);
    

    console.log("Renderizando canción:", song);

    const elements = {
        name: document.getElementById("nameSong"),
        artist: document.getElementById("artistSong"),
        album: document.getElementById("albumSong"),
        date: document.getElementById("dateSong"),
        category: document.getElementById("categorySong"),
        duration: document.getElementById("durationSong")
    };

    for (const key in elements) {
        
            elements[key].value = song[key] ;
        
    }
    fetch(`http://localhost:9000/categories/${song.id_category}`)
        .then(response => response.json())
        .then(category => {
            const categoryElement = document.getElementById("categorySong");
            if (categoryElement) {
                categoryElement.value = category.name; // Asigna el nombre de la categoría
                localStorage.setItem('idCategoria', song.id_category); // Guarda el id en localStorage
            }
        })
        .catch(error => console.error("Error al obtener la categoría:", error));
}



function saveSong() {
        const id = localStorage.getItem('idSong');
        const idCategory = localStorage.getItem('idCategoria');
    
        if (!id || !idCategory) {
            alert("No se encontró la información de la canción.");
            return;
        }
    
        const newName = document.getElementById("nameSong").value.trim();
        const newArtist = document.getElementById("artistSong").value.trim();
        const newDuration = document.getElementById("durationSong").value.trim();
        const newDate = document.getElementById("dateSong").value;
        const newAlbum = document.getElementById("albumSong").value.trim();
    
        if (!newName || !newArtist || !newDuration || !newDate || !newAlbum) {
            alert("Por favor, complete todos los campos.");
            return;
        }
    
        const updatedSong = {
            name: newName,
            artist: newArtist,
            duration: newDuration,
            id_category: idCategory,
            date: newDate,
            album: newAlbum
        };
    
        const apiUrl = `http://localhost:9000/songs/${id}`;
    
        fetch(apiUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedSong)
        })
        .then(response => {
            if (!response.ok) throw new Error("Error al actualizar la canción");
            return response.text();
        })
        .then(text => {
            if (!text) {
                alert("Fallo al actualizar la canción. Puede que ya exista con ese nombre.");
                return;
            }
            alert("Canción actualizada correctamente.");
            window.location.href = 'putSong.html';
        })
        .catch(error => {
            console.error("Error en la actualización:", error);
            alert("Error al actualizar la canción.");
        });
    }