import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/dannaHeader';
import DataStore from "../util/DataStore";

class ViewAllClientProfiles extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount','confirmRedirect','submitFormData', 'redirectEditClientProfile','redirectAllClientProfiles',
                'redirectCreateAppointment','redirectAllAppointments', 'redirectServiceMenu', 'logout','setPlaceholders'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayEvents);
        this.header = new Header(this.dataStore);

    }

    /**
     * Once the client is loaded, get the profile metadata.
     */
    async clientLoaded() {
        const identity = await this.client.getIdentity();
        const profile = await this.client.getProfile(identity.email);
        const appointments = await this.client.getAllAppointments();
        this.dataStore.set("email", identity.email);
        this.dataStore.set('profile', clientProfile);
        this.displayAppointments();
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


    displayAppointments(){
            const appointments = this.dataStore.get("appointments");
            console.log(appointments , "from displayAppointments");
            if (appointments == null) {
                document.getElementById("appointments-list").innerText = "No Appointments found";
            }
            document.getElementById("appointments-list").innerHTML = this.getHTMLForSearchResults(appointments);
    }

    getHTMLForSearchResults(searchResults) {
     console.log(searchResults , "from getHTMLForSearchResults");
            if (!searchResults || !searchResults.allAppointmentsList || searchResults.allAppointments.length === 0) {
                return '<h4>No results found</h4>';
            }
            let html = "";
            for (const res of searchResults.allAppointmentsList) {
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


    async addName(){
        const client = this.dataStore.get("client");
        if (name == null) {
            document.getElementById("client").innerText = "Client can not be null";
        }
        document.getElementById("client").innerText = client;
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
    const allAppointments = new allAppointments();
    allAppointments.mount();
};

window.addEventListener('DOMContentLoaded', main);