//MENÚ
function openArtistMenu(index){
    var a = document.getElementById("artistMenu"+index)

    if(a.style.display == "block"){
        a.style.display = "none"; 
    }else{
        a.style.display = "block";
    }
}

function openArtistDelete(id){
    var boxDelete = document.getElementById("openArtistDelete");
    let boton = document.getElementById("eliminar");
    boton.onclick = function(){
        eliminarArtista(id);
    }
    if(boxDelete.style.display == "block"){
        boxDelete.style.display = "none";
    }else{
        boxDelete.style.display = "block";
    }

}

function openCreateArtist(){
    let newCat = document.getElementById("newArtSection");
    if(newCat.style.display == "block"){
        newCat.style.display = "none";
    }else{
        newCat.style.display = "block";
    }
}

async function openModifyArtist(){
    let newCat = document.getElementById("modifyArtSection");
    const id = localStorage.getItem('idArtist');
    const artistResponse = await fetch(`http://localhost:9000/artists/`+id);
    const artistData = await artistResponse.json();

        if (!artistData || artistData.length === 0) {
            console.error('No se encontró al artista');
            return;
        }else{
        console.log('Artista encontrado:', artistData);
        }
        document.getElementById("updateNameArt").value = artistData.name
    if(newCat.style.display == "block"){
        newCat.style.display = "none";
    }else{
        newCat.style.display = "block";
    }
}


//CHARGE
document.addEventListener('DOMContentLoaded', function(){
    if(document.getElementById('artists-container')){
        getAll();
    }else{
        getArtist();
    }
})

//GET ALL

function getAll(){
    const url = `http://localhost:9000/artists`;

    fetch(url)
        .then(function(response){
            if(response.ok){
                console.log(response);
                return response.json();
            }else{
                throw new Error('Error en la llamada a la API:', response.statusText);
            }
        })
        .then(function(artists){
            console.log('Artistas recibidos:', artists);
            renderArtists(artists);
        })
        .catch(function(error){
            console.error('Error al obtener los artistas:', error);
        });
}

function renderArtists(artists){

    var container = document.getElementById('artists-container');
    container.innerHTML = '';

    artists.forEach(function(artist,index){
        var artistElement = document.createElement('div');
        artistElement.classList.add('artists');

        artistElement.innerHTML = `
        <h3>${artist.name}</h3>
        <button type="button" onclick="openArtistMenu(${index})">
            <i class="bi bi-list"></i>
        </button>

        <ul id="artistMenu${index}" class="submenu">
            <li><a onclick="showArtist(${artist.id})">Mostrar artista</a></li>
            <li><a onclick="modifyArtist(${artist.id})">Modificar artista</a></li>
            <li><a onclick="openArtistDelete(${artist.id})">Eliminar artista</a></li>
        </ul>
            `;
        container.appendChild(artistElement);
    });

}

//GET BY ID
function showArtist(id){
    localStorage.setItem('idArtist',id);
    window.location.href = 'showArtist.html';
}

function getArtist(){
    idArtist = localStorage.getItem('idArtist')
    const url = `http://localhost:9000/artists/${idArtist}`;

    fetch(url)
    .then(function(response){
        if(response.ok){
            return response.json();
        }else{
            throw new Error( 'Error en la respuesta de la API: ', + response.statusText);
        }
    })
    .then(function(artist){
        renderSingleArtist(artist);
    })
    .catch(function(error){
        console.error(' Error al cargar al artista:', error);
            const card = document.getElementById('card');
            if (card) {
                card.innerHTML = '<p>Error al cargar al artista</p>';
            }
    });
}

async function renderSingleArtist(artist){
    const nameElement = document.getElementById('name');
    const listElement = document.getElementById('lista');
    var li;
    nameElement.innerText = artist.name;
    
    const songResponse = await fetch(`http://localhost:9000/songs`);
    if (!songResponse.ok) throw new Error("Error al obtener las canciones");          
    const songData =  await songResponse.json();

    
    if (!songData || songData.length === 0) {
        listElement.innerHTML = '<li>No hay canciones disponibles</li>';
        return;
    }
    listElement.innerHTML = '';
    songData.forEach(song => {
        song.artistDTOList.forEach(i => {
            if(artist.id == i){
                li = document.createElement('li');
                li.textContent = song.name;
                li.onclick = function() {
                    localStorage.setItem('idSong', song.id);
                    window.location.href = 'showSong.html';
                };
                listElement.appendChild(li);       
            }else{
                listElement.innerHTML = "Este artista no tiene canciones aún...";    
            }    
        });
    });
}

//DELETE

function eliminarArtista(id){
    const url = `http://localhost:9000/artists/${id}`;
    
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
            throw new Error ('Error al eliminar al artista')
        }
    })
    .then(data => {
        alert("Artista eliminado correctamente");
        getAll()
        document.getElementById('openArtistDelete').style.display = 'none';
    })
    .catch(error => {
        alert('Error al eliminar al artista');
    });

}

//POST

function createArtist(){
    const url = `http://localhost:9000/artists`
    let newName = document.getElementById("newNameArtist").value;
    let message = document.getElementById("messageArtist");

    if(!newName.trim()){
        message.innerHTML = "Por favor ingrese un nombre";
    }else{
        const newArtist = {
            name:newName,
            id_songDTO: []
        }
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newArtist)
        })
        .then(response => {
            if (!response.ok) {
                
                if (response.status === 409) {
                    alert('Fallo al crear al artista. Ya existe');
                } else {
                    alert('Error al crear al artista');
                }
                throw new Error('Error al crear el artista');
            }
            return response.json(); 
        })
        .then(data => {
            if(data){
            alert('Artista creado correctamente');
            window.location.href = 'getArtists.html';
            }
        })
        .catch(error => {
            console.log(error);
            
            alert('Error al crear al artista');
        });
    }
}

//PUT

function modifyArtist(id) {
    localStorage.setItem('idArtist', id);
    openModifyArtist();
}


async function guardarNombreArtist(){
    const id = localStorage.getItem('idArtist');    
    let updateNameArt = document.getElementById("updateNameArt").value;
    const apiUrl = `http://localhost:9000/artists/`+id;
    const message = document.getElementById("messageUpdate");

    if(!updateName.trim()){
        message.innerHTML = "Por favor, ingrese un nombre";
        return;
    }else{
        fetch(apiUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({name: updateNameArt})
        })
        .then(response => {
            if (response.ok) {
                return response.text(); 
            } else {
                throw new Error('Error al actualizar el nombre');
            }
        })
        .then(text => {
            if (!text) {
            
                alert('Fallo al actualizar el nombre. Ya existe');
                return;
            }
            return JSON.parse(text); 
        })
        .then(data => {
            if (data) {
                alert('Artista actualizado correctamente');
                window.location.href = 'getArtists.html';
            }
        })
        .catch(error => {
            console.log(error);
            
            alert('Error al actualizar el nombre');
        });
        
    }

    
}
