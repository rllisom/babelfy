document.addEventListener('DOMContentLoaded', function() {
    getSongs();
 });
 
 
 //GET ALL
 function getSongs() {
     const url = 'http://localhost:9000/songs';
 
     fetch(url)
         .then(function(response) {
             if (response.ok) {
                 return response.json();
             } else {
                 throw new Error('Error en la llamada a la API: ' + response.statusText);
             }
         })
         .then(function(songs) {
             console.log("Canciones recibidas:", songs);
             renderSongs(songs);
         })
         .catch(function(error) {
             console.error('Error al obtener las canciones:' + error);
         });
 }
 
     function renderSongs(songs) {
         var container = document.getElementById('songs-container');
         container.innerHTML = '';
         songs.forEach(function(song,index) {
             var songElement = document.createElement('div');
             songElement.classList.ad
 
         });
     }
 
 //DELETE
 
 function eliminarCancion(id) {  
     const url = 'http://localhost:9000/songs/' + id;
     fetch(url, {
         method: 'DELETE',
         headers: {
             'Content-Type': 'application/json'
         }  
     })
     .then(response => {
         if(response.ok){
             return response.json();
         }else{
             throw new Error("Error al eliminar la canción");
         }
     })
     .then(data => {
         alert("Canción eliminada correctamente")
         getSongs();
     })
     .catch(error => {
         alert("Error al eliminar la canción")
     });
 
 }
 