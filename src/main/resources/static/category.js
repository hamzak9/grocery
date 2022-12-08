window.onload = async function() {
        fetch("http://localhost:8080/item/getAllItems")
            .then((response) => response.text())
            .then((result) => {
                const res = JSON.parse(result); // store local cache ?
                createCards(res);

                console.log(res);
            })
            .catch((error) => console.log("error Getting list of items", error));


};

function createCards(res){
    for(let i=0;i<res.length;i++){
        let currentJson = res[i];
        const container = document.getElementById("productContainer");

        let cardDeck = document.createElement("div");
        cardDeck.setAttribute("class","card-deck");

        const card = document.createElement("div");
        card.setAttribute("class","card");

        const img = document.createElement("img");
        img.setAttribute("class","card-img-top");
        img.setAttribute("src",currentJson["asset"]);

        const body = document.createElement("div");
        body.setAttribute("class","card-body");

        const h5 = document.createElement("h5");
        h5.setAttribute("id","productName");
        h5.setAttribute("class","card-title");
        h5.innerHTML = currentJson["name"];

        const text = document.createElement("p");
        text.setAttribute("class","card-text");
        text.innerHTML = currentJson["description"];

        const addToCart = document.createElement("button");
        addToCart.setAttribute("type","button");
        addToCart.setAttribute("class","btn btn-primary");
        addToCart.innerHTML="Add to Cart";

        const footer = document.createElement("div");
        footer.setAttribute("class","card-footer");

        if(i%3==0){
            console.log("ROW IS FULL");
            container.appendChild(cardDeck);
            cardDeck.appendChild(card);

        }
        else{
           cardDeck =  document.getElementById("productContainer").lastElementChild;
        }

        cardDeck.appendChild(card);
        card.appendChild(img);
        card.appendChild(body);

        body.appendChild(h5);
        body.appendChild(text);

        card.appendChild(footer);
        footer.append(addToCart);

    }


}

function removeOne(id){


}
function addOne(id){

}

function addToCart(id){

}



