let likeButton = document.querySelector(".like i");
let likeCount = $(".like-count");
let likeWrapper = document.querySelector(".like");
let hasBeenClicked = false;

function likeClickHandler() {
  let likes = likeCount.innerText;
  likes = parseInt(likes);

  if (hasBeenClicked) {
    likes--;
    likeWrapper.classList.remove("liked");
    likeButton.classList.remove("fas");
    likeButton.classList.add("far");
    hasBeenClicked = false;
  } else {
    likes++;
    likeWrapper.classList.add("liked");
    likeButton.classList.remove("far");
    likeButton.classList.add("fas");
    hasBeenClicked = true;
  }

  likeCount.innerHTML = likes + " <strong>likes</strong>";
}

likeButton.addEventListener("click", likeClickHandler);
