
console.log("ADMIN ORDERS JS LOADED");
console.log("FullCalendar:", typeof FullCalendar);

 let activeEventId = null;

function csrfHeaders() {
  const token = document.querySelector("meta[name='_csrf']")?.content;
  const header = document.querySelector("meta[name='_csrf_header']")?.content;

  return token && header ? { [header]: token } : {};
}
 function initEmojiButtons(){
            document.addEventListener("click", e =>  {
            const btn = e.target.closest(".emoji-btn");
            if(!btn) return;
            activeEventId = btn.dataset.id;
            console.log("Active event: " + activeEventId);
            })
            };

function connectWebSocket(){
    const socket = new SockJS('/ws');
    const stompClient = Stomp.over(socket);

        stompClient.connect({} , function() {
            console.log("Admin connected to WebSocket");

             stompClient.subscribe('/topic/orders/pending', function (message) {
            const order = JSON.parse(message.body);

            window.initialPendingCount++;
            updatePendingBadge(window.initialPendingCount);
            addPendingOrder(order);
            });


            stompClient.subscribe('/topic/orders/approved', function (message){
            const order = JSON.parse(message.body);
           if(!window.calendar.getEventById(order.id)){
        window.calendar.addEvent({
            id: order.id,
            title: order.character + " (" + order.childName + ")",
            start: order.startTime,
            end: order.endTime,
            color: orderStatusColor(order)
            });
            }
        });
    });
    }
function approveOrder(orderId, performer){
fetch(`/api/admin/orders/${orderId}/approved/${performer}`, {
method: 'POST',
headers: csrfHeaders()})
.then(async response => {
console.log("FETCH STATUS:", response.status);
if(!response.ok) {
let message = "Užsakymo priimti nepavyko";
try{
    const error = await response.json();
    message = error.message || message;
    } catch (e){}

throw new Error(message);
}
return response.json();
})
.then(order => {
console.log("APPROVED ORDER:", order);
removePendingOrder(orderId);
window.initialPendingCount--;
updatePendingBadge(window.initialPendingCount);
})
.catch(err => {
    console.log("❌ APPROVE ERROR:", err);
    alert(err.message);
});
}
function rejectOrder(orderId){
    fetch(`/api/admin/orders/${orderId}/rejected`, {
    method: 'POST',
    headers: csrfHeaders()})
    .then(response => {
    if(!response.ok) throw new Error("Nepavyko atmesti užsakymo");
    })
    .then(order => {
    removePendingOrder(orderId);
    window.initialPendingCount--;
    updatePendingBadge(window.initialPendingCount);
    })
    .catch(err => alert("Klaida atmetant užsakymą"));
}



function updatePendingBadge(count){
    const badge = document.getElementById("pending-count")
    if(!badge)
    return;

    if(count > 0){
    badge.style.display = "inline-block ";
    badge.innerText = count;
    }
    else {
    badge.style.display = "none";
    }

    };




    function closePendingModal(){
    document.getElementById("pending-modal").style.display = "none";
    }
    function formatDateTime(value){
    if(!value) return "";

    console.log("FormatDateTime working")
    const d = new Date(value);
    if(isNaN(d)){
    console.error("Netinkama data:", value);
    return value;
    }
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, "0");
    const day = String(d.getDate()).padStart(2,"0");
    const hours = String(d.getHours()).padStart(2, "0");
    const minutes = String(d.getMinutes()).padStart(2, "0");
    return `${year}-${month}-${day}   ${hours}:${minutes}`;
    }
    function addPendingOrder(order){
    console.log("Add pending order: ", order);
    const list = document.getElementById("pending-list");
    const item = document.createElement("li");
    item.className="pending-item";
    item.dataset.orderId=String(order.id);
    item.orderData = order;
    const start = formatDateTime(order.startTime);
    const end = formatDateTime(order.endTime);
    console.log("ORDER:", order);
        console.log("START:", order.startTime);
        console.log("END:", order.endTime);

    item.innerHTML=`
    <b>${order.childName}</b>
    (${order.character})</br>
    ${start}</br>
    ${end}<br>
    <button onclick ="showPerformerButton(${order.id})">Priimti</button>
    <button onclick ="rejectOrder(${order.id})">Atmesti</button>
    <button onclick ="showOrderInfo(this)">Peržiūrėti</button>`;
    list.appendChild(item);
    }
    function removePendingOrder(orderId){
    console.log("Remove pending order", orderId);
    const selector = `.pending-item[data-order-id='${String(orderId)}']`;
    console.log("Query" + selector);
    const item = document.querySelector(selector);
    if(!item){
    console.error("Pending item not found");
    return;
    }
    item.remove();
    console.log("Item removed");
    }
    function showOrderInfo(button){

    const item = button.closest(".pending-item");
    const order = item.orderData;
   /* const modal = document.querySelector('[data-modal="order-view"]');*/
    const body = document.getElementById("order-view-body");

    body.innerHTML = `
    <p><b>Vaiko vardas:</b> ${order.childName}</p>
    <p><b>Vaiko amžius:</b> ${order.age}</p>
    <p><b>Vaikų kiekis:</b> ${order.amountOfChildren}</p>
    <p><b>Šventės žanras:</b> ${order.orderGenre}</p>
    <p><b>Personažas:</b> ${order.character}</p>
    <p><b>Adresas:</b> ${order.location}</p>
    <p><b>Telefonas:</b> ${order.phoneNumber}</p>
    <p><b>Elektroninis paštas:</b> ${order.email}</p>
    <p><b>Pastabos:</b> ${order.notes}</p>
    <p><b>Laikas:</b> ${formatDateTime(order.startTime)} - ${formatDateTime(order.endTime)}</p>
    <br>
    `;
   modalManager.open("order-view");


    }
    function backToList() {
        document.getElementById("pending-list").classList.remove("hidden");
        document.getElementById("order-details").classList.add("hidden");
        document.getElementById("modal-title").textContent = "Naujos užklausos";
    }
    function showPerformerButton(orderId) {
    const item = document.querySelector(`.pending-item[data-order-id='${orderId}']`);
    item.innerHTML =
    `<b>Pasirinkti vedeja</b><br>
    <button onclick ="approveOrder(${orderId}, 'LIVETA')">LIVETA</button>
    <button onclick ="approveOrder(${orderId}, 'MEIDA')">UGNĖ</button>
   `;
    }
    function orderStatusColor(order){
    if(!order.performer){
    return '#FF3B3B';
    }
    if(order.performer === 'LIVETA'){
    return'#27F595';  }
    if(order.performer === 'MEIDA'){
    return '#2E27F5' ;
    }
    }








document.addEventListener('DOMContentLoaded', function(){

/* --MODAL logika veikianti, perkeliama i atskira .js--
  const btn = document.getElementById("pending-btn");
  const modal = document.getElementById("pending-modal");
  const closeBtn = document.getElementById("close-pending");
  const modalContent = document.getElementById("modal-content");

  if (btn && modal) {
    btn.addEventListener("click", (e) => {
    e.stopPropagation();
      modal.classList.toggle("hidden");
    });
  }

  if (closeBtn && modal) {
    closeBtn.addEventListener("click", () => {
      modal.classList.add("hidden");
    });

  }
  if(modal){
  modal.addEventListener("click", () => {
  modal.classList.add("hidden");
  });
  }
  if(modalContent){
  modalContent.addEventListener("click", (e) => {
  e.stopPropagation();
  });
  }
  */
  document.querySelector(".modal-content").addEventListener("click", e=>e.stopPropagation());
    const calendarEl = document.getElementById('calendar');
        console.log("calendarEl:", calendarEl);



        const calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },
        initialView: 'dayGridMonth',
        height: 'auto',
        events: (window.initialOrders || [])
        .filter(o => o.status === 'PRIIMTAS')
        .map(o =>({
        id: o.id,
        title: o.childName + " (" + o.character + ")",
        start: o.startTime,
        end: o.endTime,
        color: orderStatusColor(o),
        })),

        dateClick: function(info){
             calendar.changeView('timeGridDay', info.dateStr);
        },
        eventClick: function(info){
            const currentView = calendar.view.type;
            const orderId = info.event.id;
                console.log("Current view type:", currentView);
                console.log("Event Object:", info.event);
                console.log("Event id:", orderId);
         /*   if(currentView !== 'timeGridDay'){
            calendar.changeView('timeGridDay', info.event.start);
            return;
           }
*/
            if(!orderId){
            alert("Klaida: nerastas uzsakymo id!");
            return;
            }

            window.location.href = "/admin/orders/" + orderId;
            },


           /* eventContent: function(arg){
            const container = document.createElement("div");
            container.classList.add("fc-event-custom");
            const title = document.createElement("div");
            title.textContent = arg.event.title;
             const time = document.createElement("div");
                time.textContent = arg.event.start.toLocaleTimeString([], {
                hour: '2-digit',
                minute: '2-digit'

                });



           /* const editTextBtn = document.createElement("button");
            editTextBtn.textContent = "✏️";
            editTextBtn.className = "emoji-btn";
            //editTextBtn.dataset.open="edit-text";
            editTextBtn.dataset.id=arg.event.id;

            const editImageBtn = document.createElement("button");
            editImageBtn.textContent ="🖼️";
            editImageBtn.className="emoji-btn";
            //editImageBtn.dataset.open="edit-images";
            editImageBtn.dataset.id = arg.event.id;
            container.appendChild(title);
            container.appendChild(time);


           // container.appendChild(editTextBtn);
           // container.appendChild(editImageBtn);
            return { domNodes: [container] };
            }
            */


        });
    console.log("initialPendingOrders:", window.initialPendingOrders);
    console.log("TYPE:", typeof window.initialPendingOrders);
    console.log("IS ARRAY:", Array.isArray(window.initialPendingOrders));
    console.log("VALUE:", window.initialPendingOrders);
        (window.initialPendingOrders || []).forEach(addPendingOrder);
        updatePendingBadge(window.initialPendingCount || 0);

        calendar.render();
        window.calendar = calendar;
        connectWebSocket();
        modalManager.init();
       // initEmojiButtons();

});




