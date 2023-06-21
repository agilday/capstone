import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class GetServiceMenu extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayServices', 'redirectCreateService', 'redirectAllClientProfiles',
                'redirectCreateAppointment', 'redirectCreateClientProfile','redirectAllAppointments', 'logout'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayServices);
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


    displayServices(){
            const services = this.dataStore.get("services");
            console.log(services , "from displayServices");
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
            let html = "";
            for (const res of searchResults) {
                html += `
                <tr>
                <td>
                         ${res.title}
                 </td>
                 <td>
                         ${res.description}
                  </td>

                </tr>`;
            }
            return html;
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