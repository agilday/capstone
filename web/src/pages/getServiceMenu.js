import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/dannaHeader';
import DataStore from "../util/DataStore";

class getServiceMenu extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','confirmRedirect','submitFormData', 'redirectEditClientProfile','redirectAllClientProfiles',
                'redirectCreateAppointment','redirectAllAppointments', 'redirectServiceMenu', 'logout','setPlaceholders'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayServices);
        this.header = new Header(this.dataStore);

    }

    /**
     * Once the client is loaded, get the profile metadata.
     */
    async clientLoaded() {
        const identity = await this.client.getIdentity();
        const profile = await this.client.getProfile(identity.email);
        const appointments = await this.client.getServiceMenu();
        this.dataStore.set("email", identity.email);
        this.dataStore.set('profile', clientProfile);
        this.displayServices();
        //console.log(events);

    }

    /**
     * Add the header to the page and load the petproClient.
     */
    mount() {
        document.getElementById('allAppointments').addEventListener('click', this.redirectAllAppointments);
        document.getElementById('serviceMenu').addEventListener('click', this.redirectGetServiceMenu);
        document.getElementById('allClientProfiles').addEventListener('click', this.redirectAllClientProfiles);
        document.getElementById('createClientProfile').addEventListener('click', this.redirectCreateClientProfile);
        document.getElementById('logout').addEventListener('click', this.logout);
        document.getElementById('names').innerText = "Loading ...";
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
            if (!searchResults || !searchResults.allServicesList || searchResults.allServices.length === 0) {
                return '<h4>No results found</h4>';
            }
            let html = "";
            for (const res of searchResults.allServicesList) {
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


    redirectUpdateClientProfile(){
        window.location.href = '/updateClientProfile.html';
    }
    redirectAllClientProfiles(){
        window.location.href = '/allClientProfiles.html';
    }
    redirectCreateAppointment(){
        window.location.href = '/createAppointment.html';
    }
    redirectAllAppointments(){
        window.location.href = '/allAppointments.html';
    }
    redirectServiceMenu(){
        window.location.href = '/serviceMenu.html';
    }
    async logout(){
        await this.client.logout();
        if(!this.client.isLoggedIn()){
            window.location.href ='/landingPage.html';
        }

    }

}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const allServices = new allServices();
    allServices.mount();
};

window.addEventListener('DOMContentLoaded', main);