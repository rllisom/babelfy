
   
document.addEventListener('DOMContentLoaded', function() {
    if(window.location.pathname.endsWith('putSong.html')){
    getSongById();
    }

});




    function getSongById(){
        
        const id = localStorage.getItem('idSong');
        const apiUrl = `http://localhost:9000/songs/${id}`;
    
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
  
  
  //PUT



  async function renderSingleSong(song) {
            
    const idCategoria = localStorage.getItem('idCategoria');
    try {
        console.log("Canción obtenida:", song);

        const categoryResponse = await fetch(`http://localhost:9000/categories/`+idCategoria);
        if (!categoryResponse.ok) throw new Error("Error al obtener la categoría");
        
        const categoryData =  await categoryResponse.json();
        

        document.getElementById('songName').value = song.name;
        document.getElementById('author').value = song.artist;
        document.getElementById('album').value = song.album;
        document.getElementById('releaseDate').value = song.date;
        document.getElementById('duration').value = song.duration;
        document.getElementById('category').value = categoryData.name;
        
    } catch (error) {
        console.error(' Error al renderizar la canción:', error);
    }


    
}


  function putSong(id, id_category){
    localStorage.setItem('idCategoria', id_category);
    localStorage.setItem('idSong', id);
    window.location.href = 'putSong.html';

}

function saveSong(){

    const id = localStorage.getItem('idSong');
    

    let nameSong = document.getElementById("songName").value;
    let artistSong = document.getElementById("author").value;
    let durationSong = document.getElementById("duration").value;
    let idCategory = localStorage.getItem('idCategoria');    
    let dateSong = document.getElementById("releaseDate").value;
    let albumSong = document.getElementById("album").value;
    
    
    const apiUrl = `http://localhost:9000/songs/${id}`;
   
 
    if(nameSong.trim() === "" || artistSong.trim() === "" || durationSong.trim() === "" || dateSong.trim() === "" || albumSong.trim() === ""){
       alert('No pueden haber campos vacíos');
       window.location.href = 'putSong.html';

        return;    
    } else{

        const saveSong = {
            name: nameSong,
            artist: artistSong,
            duration: durationSong,
            id_category: idCategory,
            date: dateSong,
            album: albumSong
        };            

        fetch(apiUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(saveSong)
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
                alert('Fallo al actualizar la canción. No ha habido cambios');
                return;
            }
            return JSON.parse(text); // Convertimos solo si hay contenido JSON
        })
        .then(data => {
            if (data) {
                alert('Canción actualizada correctamente');
                window.location.href = 'getSongs.html';
            }
        })
        .catch(error => {
            console.log(error);
            
            alert('Error al actualizar la canción');
        });
        
    }

    
}

