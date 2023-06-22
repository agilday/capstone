import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class GetServiceMenu extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'deleteService', 'getServiceMenu', 'redirectCreateService', 'redirectAllClientProfiles',
                'redirectCreateAppointment', 'redirectCreateClientProfile','redirectAllAppointments', 'logout'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.getServiceMenu);
        this.header = new Header(this.dataStore);

    }

    /**
     * Once the client is loaded, get the profile metadata.
     */
    async clientLoaded() {
        const identity = await this.client.getIdentity();
        const services = await this.client.getServiceMenu();
        this.dataStore.set("email", identity.email);
        this.dataStore.set("services", services);

    }

    /**
     * Add the header to the page and load the petproClient.
     */
    mount() {
        document.getElementById('AllAppointments').addEventListener('click', this.redirectAllAppointments);
        document.getElementById('CreateAppointment').addEventListener('click', this.redirectCreateAppointment);
        document.getElementById('CreateService').addEventListener('click', this.redirectCreateService);
        document.getElementById('AllClientProfiles').addEventListener('click', this.redirectAllClientProfiles);
        document.getElementById('CreateClientProfile').addEventListener('click', this.redirectCreateClientProfile);
        document.getElementById('logout').addEventListener('click', this.logout);
        this.client = new petproClient();
        this.clientLoaded();
    }


    getServiceMenu(){
            const services = this.dataStore.get("services");
            console.log(services , "from getServiceMenu");
            if (services == null) {
                document.getElementById("services-list").innerText = "No services found";
            }
            document.getElementById("services-list").innerHTML = this.getHTMLForSearchResults(services);
    }

    getHTMLForSearchResults(searchResults) {
     console.log(searchResults , "from getHTMLForSearchResults");
            if (!searchResults) {
                return '<h4>No results found</h4>';
            }
            const servicesTable = document.getElementById("Toptable");
            let html = "";
            for (const res of searchResults) {
                  const row = document.createElement('tr');
                  row.innerHTML = `<td><a id="title-${res.title}" href="#offcanvas-update-record" data-bs-toggle="offcanvas" data-toggle="collapse">${res.title}</a></td>
                  <td id="description-${res.description}">${res.description}</td>
                  <td><button id="deleteButton-${res.title}" data-title="${res.title}">Delete</button></td>
                  </tr>`;
                servicesTable.appendChild(row);
                const deleteButton = `#deleteButton-${res.title}`;
                document.getElementById(`deleteButton-${res.title}`).addEventListener('click', () => this.deleteService(res.title));
            }
            return html;
        }


    async deleteService(title){
            if(confirm("Delete this service?")){
                console.log(title);
                await this.client.deleteService(title);
                alert(title + " has been deleted.");
                location.reload();
            }
        }

    redirectAllClientProfiles(){
        window.location.href = '/AllClientProfiles.html';
    }
    redirectCreateAppointment(){
        window.location.href = '/CreateAppointment.html';
    }
    redirectCreateService(){
        window.location.href = '/CreateService.html';
    }
    redirectCreateClientProfile(){
        window.location.href = '/CreateClientProfile.html';
    }
    redirectAllAppointments(){
        window.location.href = '/AllAppointments.html';
    }
    async logout(){
        await this.client.logout();
        if(!this.client.isLoggedIn()){
            window.location.href ='/LandingPage.html';
        }

    }

}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const allServices = new GetServiceMenu();
    allServices.mount();
};

window.addEventListener('DOMContentLoaded', main);