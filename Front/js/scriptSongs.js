//MENÚ DE OPCIONES

function abrirMenu(index){
    let menu = document.getElementById("menu"+index);
    if(menu.style.display == "block"){
        menu.style.display = "none";
    }else{
        menu.style.display = "block";
    }
 }
 
 function createMenu(){
    renderCategoryOptions();
    let menu = document.getElementById("addBox");
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


 document.addEventListener('DOMContentLoaded', function() {
    if (document.getElementById('song-container')) {
        getSongs(); 
    } else if (document.getElementById('card')) {
        getSongById();
    }

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
                    <li><a onclick="getSong(${song.id},${song.id_category})">Mostrar información</a></li>
                    <li><a onclick="putSong(${song.id},${song.id_category})">Cambiar información</a></li>
                    <li>
                        <a type="button" onclick="mensajeConfirmacion(${song.id})">Eliminar canción</a>
                    </li>
                </ul>`;
                container.appendChild(songElement);
         });
     }


     //GET BY ID

     function getSong(id, id_category){
        localStorage.setItem('idCategoria', id_category);
        localStorage.setItem('idSong', id);
        window.location.href = 'showSong.html';
        

    }

  
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
        



        async function renderSingleSong(song) {
            
            const idCategoria = localStorage.getItem('idCategoria');
            try {
                console.log("Canción obtenida:", song);
        
                const categoryResponse = await fetch(`http://localhost:9000/categories/`+idCategoria);
                if (!categoryResponse.ok) throw new Error("Error al obtener la categoría");
                
                const categoryData =  await categoryResponse.json();

                document.getElementById('songName').textContent = song.name;
                document.getElementById('author').textContent = song.artist;
                document.getElementById('album').textContent = song.album;
                document.getElementById('releaseDate').textContent = song.date;
                document.getElementById('duration').textContent = song.duration;
                document.getElementById('category').textContent = categoryData.name;
            } catch (error) {
                console.error(' Error al renderizar la canción:', error);
            }
            
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
 
    async function renderCategoryOptions() {
        const categoryResponse = await fetch(`http://localhost:9000/categories`);
        const categoryData = await categoryResponse.json();
        
        if (!categoryData || categoryData.length === 0) {
            console.error('No se encontraron categorías');
            return;
        }

        console.log('Categorías recibidas:', categoryData);
       
        let selectElement = document.getElementById('categorySongOptions');
        selectElement.innerHTML = ''; 

        if(window.location.pathname.endsWith('putSong.html')){
        }else{
        let defaultOption = document.createElement('option');
        defaultOption.value = "";
        defaultOption.textContent = "Selecciona una categoría";
        selectElement.appendChild(defaultOption);
        }
        
        categoryData.forEach(category => {
            
            let option = document.createElement('option');
            option.value = category.id;
            option.textContent = category.name;
            selectElement.appendChild(option);
            
            
        });

        selectElement.addEventListener('change', function() {
            const selectedCategoryId = selectElement.value;
            localStorage.setItem('idCategoria', selectedCategoryId);
            console.log('ID de categoría guardada:', selectedCategoryId);
        });
        return true;
    }

    function createSong(){

        renderCategoryOptions();
        
        const apiUrl = `http://localhost:9000/songs`;
        let newName = document.getElementById("songName").value;
        let newArtist = document.getElementById("author").value;
        let newDuration = document.getElementById("duration").value;
        let newCategory = localStorage.getItem('idCategoria');
        let newDate = document.getElementById("releaseDate").value;
        let newAlbum = document.getElementById("album").value;
        
        const message = document.getElementById("messageError");  
     
        
        if(!newName.trim() || !newArtist.trim() || !newDuration.trim() || !newDate.trim() || !newAlbum.trim() || !newCategory){
            message.innerHTML = "Por favor, rellene todas las casillas";
            return;
        }else{
    
            const newSong = {
                name: newName,
                artist: newArtist,
                duration: newDuration,
                id_category: newCategory,
                date: newDate,
                album: newAlbum
            };            
    
            fetch(apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newSong)
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

  