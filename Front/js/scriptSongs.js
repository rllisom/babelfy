document.addEventListener('DOMContentLoaded', function() {
    getSongs();
 });
 
 
 //GET ALL
 function getSongs() {
     const url = `http://localhost:9000/songs`;
 
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

        
     async function renderCategoryOptions() {
        renderCategoryOptions();
        const categoryResponse = await fetch(`http://localhost:9000/categories`);
        const categoryData = await categoryResponse.json();
        if (!categoryData || categoryData.length === 0) {
            console.error('No se encontraron categorías');
            return;
        } 
        console.log('Categorías recibidas:', categoryData);
        var container = document.getElementById('categorySongOptions');
        container.innerHTML = ''; 
    
        var selection = document.createElement('select');
    
        categoryData.forEach(function(category) { 
            var option = document.createElement('option');
            option.value = category.id; 
            option.textContent = category.name; 
            selection.appendChild(option); 
        });
    
        container.appendChild(selection); 
        localStorage.setItem('idCategoria',categoryData.id);
        window.location.href = 'getSongs.html';
        
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
        let mensaje = document.getElementById("open_PopUp");
        let boton = document.getElementById("eliminar");
        boton.onclick = function(){
            eliminarCancion(id);
        }
        if(mensaje.style.display == "flex"){
            mensaje.style.display = "none";
        }else{
            mensaje.style.display = "flex";
        }
     }

        function getSong(id){
            localStorage.setItem('idSong', id);
            window.location.href = 'showSong.html';
        }
 //DELETE
 
 function eliminarCancion(id) {  
     const url = `http://localhost:9000/songs/${id}`;
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
         document.getElementById('open_PopUp').style.display = 'none';
     })
     .catch(error => {
         alert("Error al eliminar la canción")
         document.getElementById('open_PopUp').style.display = 'none';
     });
 
 }

 //POST 
 function createCategory(){
    renderCategoryOptions();
    const idCategoria = localStorage.getItem('idCategoria');
    const apiUrl = `http://localhost:9000/songs`;
    let newName = document.getElementById("nameSong").value;
    let newArtist = document.getElementById("artistSong").value;
    let newDuration = document.getElementById("durationSong").value;
    let newCategory = document.getElementById("categorySongOptions");
    newCategory = idCategoria;
    let newDate = document.getElementById("dateSong").value;
    let newAlbum = document.getElementById("albumSong").value;
    
    const message = document.getElementById("message");
 
 
    if(!newName.trim()){
        message.innerHTML = "Por favor, ingrese un nombre";
        return;
    }else{
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: newName} , {artist: newArtist}, {duration: newDuration},
                 {category: newCategory}, {date: newDate}, {album: newAlbum})
        })
        .then(response => {
            if(response.ok){
                return response.text();
            }
            throw new Error('Error al crear el nombre');
        })
        .then(text => {
            if(!text){
                alert('Fallo al crear la canción. Ya existe');
                return;
            }
            return JSON.parse(text);
        })
        .then(data => {
            if(data){
            alert('Canción creada correctamente');
            window.location.href = 'getSongs.html';
            }
        })
        .catch(error => {
            alert('Error al crear la canción');
        });
    }
}
 