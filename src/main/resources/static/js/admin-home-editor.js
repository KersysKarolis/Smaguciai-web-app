document.addEventListener("DOMContentLoaded", () =>{
loadContentList();
loadImageList();
initHomeTextSave();
initHomeImageUpload();
homeImageDelete();




});
function loadImageList(){
const select = document.getElementById("image-select");
if(!select) return ;
select.innerHTML ="";

fetch("/admin/content/imageList")
.then(res=> res.json())
.then(list => {
list.forEach(item => {
const opt = document.createElement("option");
opt.value = `${item.section}|${item.contentKey}`;
opt.textContent = `${item.section} - contentKey ${item.contentKey}`;
select.appendChild(opt);
});
});

select.addEventListener("change", loadCurrentImage);
select.selectedIndex = 0;
loadCurrentImage();
}
function loadCurrentImage(){
console.log("load current image triggered");
const select = document.getElementById("image-select");
const current = document.getElementById("current-image-preview");
console.log("Select value:", select.value);
if(!select.value) {
current.src="";
return;
}
const [section, contentKey] = select.value.split("|");
console.log("Section:", section);
console.log("ContentKey:", contentKey);
fetch(`/admin/content/getImage?section=${section}&contentKey=${contentKey}`)
.then(res=> {
console.log("Status:", res.status);
if(res.status === 204) return null;
return res.json();
})
.then(data=> {
const noImage = document.getElementById("no-image-text");
const current = document.getElementById("current-image-preview");
        if (data && data.fileName){
current.src = data.fileName + "?t=" + new Date().getTime();
noImage.style.display="none";
        } else {
    current.src = "";
    noImage.style.display="block";
     }
     })
     .catch(err => {
     console.error("Image load error:", err)
     document.getElementById("current-image-preview").src ="";
     });
}


/*

fetch('/admin/content/imageList')
.then(res => res.json())
.then(items => {
const select = document.getElementById("image-select");
select.innerHTML = "";

items.forEach(item => {
const opt = document.createElement("option");
opt.value = item.section + "|" + item.position ;
opt.textContent = item.label;
select.appendChild(opt);
});
*/
/*

const targets = [
{section: "home.header.image", positions: [0,1,2,3]},
{section: "home.about.image", positions: [0,1,2,3]}
];
targets.forEach(t => {
t.positions.forEach(pos => {
const option = document.createElement("option");
option.value= `${t.section}|${pos}`;
option.textContent= item.label;
select.appendChild(option);
});
*/


function loadContentList () {
fetch("/admin/content/list")
.then(res => res.json())
.then(items => {
const select = document.getElementById("content-select");
if(!select) return;
select.innerHTML ="";
items.forEach(item => {
const option = document.createElement("option");
option.value= item.key;
option.textContent= item.label;
select.appendChild(option);
});
if(items.length>0){
select.value = items[0].key;
loadSelectedContent();
}
select.addEventListener("change", loadSelectedContent);
});
}
function loadSelectedContent (){
const key = document.getElementById("content-select").value;
fetch(`/admin/content/get?key=${key}`)
.then(res => res.json())
.then(data => {
document.getElementById("content-textarea").value= data.value;
document.getElementById("content-modal-title").textContent = data.label;
});
}

function initHomeTextSave(){
const btn = document.getElementById("save-text-btn");
if(!btn) return;

btn.addEventListener("click", () => {
console.log("Saved button clicked");
const key = document.getElementById("content-select").value;
const value = document.getElementById("content-textarea").value;

fetch("/admin/content/update", {
method: "POST",
headers: {"Content-type" : "application/json",
...csrfHeaders()},
body: JSON.stringify({
key,
value
})
}).then(() => {
alert ("Issaugota");
});
});

}
function initHomeImageUpload(){
const btn = document.getElementById("save-img-btn");
const input = document.getElementById("image-upload");
if(!btn || !input) return;

input.addEventListener("change", () => {
const file = input.files[0];
if(!file) return;

const preview = document.getElementById("image-preview");
preview.innerHTML="";
const img = document.createElement("img");
img.src = URL.createObjectURL(file);
img.style.width = "100px";
preview.appendChild(img);
});
btn.addEventListener("click", () => {
const key = document.getElementById("image-select").value;

if(!input.files.length || !key) return;
const [section, contentKey] = key.split("|");
const formData = new FormData();
formData.append("section", section);
formData.append("contentKey", contentKey);
formData.append("file", input.files[0]);

fetch("/admin/content/updateImage", {
method: "POST",
headers: csrfHeaders(),
body:formData
})
.then(res=>{
if(!res.ok) throw new Error(`Įkėlimas nepavyko: ${res.status}`);
alert("Nuotrauka issaugota");
resetImageUploadState();
loadCurrentImage();
})
.catch(err =>{
console.error(err);
alert("Nepavyko issaugoti nuotraukos");
});



});
}
function homeImageDelete (){
document.getElementById("delete-img-btn").addEventListener("click", () => {
const [section, contentKey] =
document.getElementById("image-select").value.split("|");
fetch("/admin/content/deleteImage", {
method: "POST",
headers: {"Content-Type": "application/json",
 ...csrfHeaders()
},
body: JSON.stringify({section, contentKey})
}).then(()=>{
alert("Isstrinta");
resetImageUploadState();
loadCurrentImage();
})
})
}
function resetImageUploadState(){
const current = document.getElementById("current-image-preview");
const input = document.getElementById("image-upload");
const uploadPreview = document.getElementById("image-preview");
if(current) current.src="";
if(input) input.value= "";
if(uploadPreview) uploadPreview.innerHTML ="";
const noImage = document.getElementById("no-image-text");
if(noImage) noImage.style.display="block";
}
function resetModalState(){
resetImageUploadState();
const select = document.getElementById("image-select");
if(select){
select.selectedIndex = 0;
}
loadCurrentImage();

}
