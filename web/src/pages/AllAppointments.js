import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class AllAppointments extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'getAllAppointments', 'redirectUpdateClientProfile','redirectAllClientProfiles', 'redirectCreateClientProfile',
                'redirectCreateAppointment','redirectAllAppointments', 'redirectGetServiceMenu', 'logout'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.getAllAppointments);
        this.header = new Header(this.dataStore);

    }

    /**
     * Once the client is loaded, get the profile metadata.
     */
    async clientLoaded() {
        const identity = await this.client.getIdentity();
        const appointments = await this.client.getAllAppointments();
        this.dataStore.set("appointments", appointments);
        this.dataStore.set("email", identity.email);

    }

    /**
     * Add the header to the page and load the petproClient.
     */
    mount() {
        document.getElementById('AllAppointments').addEventListener('click', this.redirectAllAppointments);
        document.getElementById('ServiceMenu').addEventListener('click', this.redirectGetServiceMenu);
        document.getElementById('AllClientProfiles').addEventListener('click', this.redirectAllClientProfiles);
        document.getElementById('CreateClientProfile').addEventListener('click', this.redirectCreateClientProfile);
        document.getElementById('UpdateClientProfile').addEventListener('click', this.redirectUpdateClientProfile);
        document.getElementById('CreateAppointment').addEventListener('click', this.redirectCreateAppointment);
        document.getElementById('logout').addEventListener('click', this.logout);
        this.client = new petproClient();
        this.clientLoaded();
    }


    getAllAppointments(){
            const appointments = this.dataStore.get("appointments");
            console.log(appointments , "from getAllAppointments");
            if (appointments == null) {
                document.getElementById("appointments-list").innerText = "No Appointments found";
            }
            document.getElementById("appointments-list").innerHTML = this.getHTMLForSearchResults(appointments);
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
                         ${res.client}
                 </td>
                 <td>
                         ${res.dateTime}
                  </td>
                  <td>
                         ${res.pet}
                   </td>
                    <td>
                         ${res.service}
                  </td>

                </tr>`;
            }
            return html;
        }



    redirectUpdateClientProfile(){
        window.location.href = '/UpdateClientProfile.html';
    }
    redirectAllClientProfiles(){
        window.location.href = '/AllClientProfiles.html';
    }
    redirectCreateAppointment(){
        window.location.href = '/CreateAppointment.html';
    }
    redirectCreateClientProfile(){
        window.location.href = '/CreateClientProfile.html'
    }
    redirectAllAppointments(){
        window.location.href = '/AllAppointments.html';
    }
    redirectGetServiceMenu(){
        window.location.href = '/ServiceMenu.html';
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
    const allAppointments = new AllAppointments();
    allAppointments.mount();
};

window.addEventListener('DOMContentLoaded', main);