
document.addEventListener('DOMContentLoaded', function(){

    function getCategory(){
        const apiUrl = 'http://localhost:9000/categories/{id}';

        fetch(apiUrl)
            .then(function(response){
                if(!response.ok){
                    throw new Error('Error en la respuesta de la API' + response.statusText);
                }else{
                    return response.json();
                }
            })
            .then(function(category){
                renderCategory(category);
            })
            .catch(function(error){
                console.error('Error al cargar la categoría ' + error);
                document.getElementById('card').innerHTML  = '<p>Error al cargar la categoría</p>';
            });
    }

    function renderCategory(category){


        if(!category || !category.songs){
            document.getElementById('name').innerHTML = 'No se encontró la categoría';
            document.getElementById('lista').innerHTML = '<li>No hay canciones disponibles</li>';
        }else{
         
            document.getElementById('name').innerText = category.name;
            const listaElement = document.getElementById('lista');
            listaElement.innerHTML = ''; 

            category.songs.forEach(song => {
                const li = document.createElement('li');
                li.innerText = song.name;
                listaElement.appendChild(li);
            });
        }
    }

    getCategory();

}); 

