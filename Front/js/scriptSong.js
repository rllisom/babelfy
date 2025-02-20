document.addEventListener('DOMContentLoaded', function(){

function getSongById(){
    const id = localStorage.getItem('idCancion');
    const apiUrl = `http://localhost:9000/songs/1`;

    fetch(apiUrl)
        .then(function(response){
            console.log("hola");
            return response.json();
        })
        .then(function(song){
            renderSingleSong(song);
        })
        .catch(function(error){
            console.error('Error al cargar la canción: ' + error);
           
        });

}

function renderSingleSong(song){
    console.log(song);
    

    let songName = document.getElementById('songName');
    let songArtist = document.getElementById('author');
    let songAlbum = document.getElementById('album');   
    let songReleaseDate = document.getElementById('releaseDate');
    let songDuration = document.getElementById('duration');
    let songCategory = document.getElementById('category');

    songName.innerText = song.name;
    songArtist.innerText = song.artist;
    songAlbum.innerText = song.album;
    songReleaseDate.innerText = song.releaseDate;
    songDuration.innerText = song.duration;
    songCategory.innerText = song.category;
    
       
    

   
}
getSongById()
});