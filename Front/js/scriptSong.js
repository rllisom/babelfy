document.addEventListener('DOMContentLoaded', function(){

function getSongById(){
    const id = localStorage.getItem('idCancion');
    const apiUrl = `http://localhost:9000/songs/31`;

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

async function renderSingleSong(song) {
    try {
        console.log("Canción obtenida:", song);

        const categoryResponse = await fetch(`http://localhost:9000/categories/3`);
        if (!categoryResponse.ok) throw new Error("Error al obtener la categoría");
        
        const categoryData =  await categoryResponse.json();
        console.log("Nombre obtenido:", categoryData.name);

        // 4️⃣ Insertar los datos en el HTML
        document.getElementById('songName').innerText = song.name;
        document.getElementById('author').innerText = song.artist;
        document.getElementById('album').innerText = song.album;
        document.getElementById('releaseDate').innerText = song.date;
        document.getElementById('duration').innerText = song.duration;
        document.getElementById('category').innerText = categoryData.name;
    } catch (error) {
        console.error(' Error al renderizar la canción:', error);
    }
}
getSongById()
});