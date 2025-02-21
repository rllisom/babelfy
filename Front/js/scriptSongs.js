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
         var container = document.getElementById('song-container');
         container.innerHTML = '';
         songs.forEach(function(song,index) {
             var songElement = document.createElement('div');
             songElement.classList.add('lineSong');
             songElement.innerHTML =
             `<li>${song.name}</li>
             <li>${song.artist}</li>        
             <li>${song.duration}</li>             
             <li>
             <button type="button" href="#menu${index}" onclick="abrirMenu(${index})">
             <i class="bi bi-three-dots"></i></button>
             </li>
             <ul id="menu${index}" class="submenu">
                    <li><a onclick="getSong(${song.id})">Mostrar información</a></li>
                    <li><a onclick="putSong()">Cambiar información</a></li>
                    <li>
                        <a type="button" onclick="mensajeConfirmacion(${song.id})">Eliminar canción</a>
                    </li>
                </ul>`;
                container.appendChild(songElement);
         });
     }
 
     function abrirMenu(index){
        let menu = document.getElementById("menu"+index);
        if(menu.style.display == "block"){
            menu.style.display = "none";
        }else{
            menu.style.display = "block";
        }
     }

     function mensajeConfirmacion(id){
        let mensaje = getElementById("open_PopUp");
        let boton = getElementById("eliminar");
        boton.onclick = function(){
            eliminarCancion(id);
        }
        if(mensaje.style.display == "block"){
            mensaje.style.display = "none";
        }else{
            mensaje.style.display = "block";
        }
     }

        function getSong(id){
            localStorage.setItem('idSong', id);
            window.location.href = 'showSong.html';
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
 