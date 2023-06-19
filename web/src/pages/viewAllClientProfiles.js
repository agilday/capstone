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
        const clientprofiles = await this.client.getAllClientProfiles();
        this.dataStore.set("email", identity.email);
        this.dataStore.set('profile', clientProfile);
        this.displayClientProfiles();
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


    displayClientProfiles(){
            const clientprofiles = this.dataStore.get("clientprofiles");
            console.log(clientprofiles , "from displayClientProfiles");
            if (clientprofiles == null) {
                document.getElementById("client-profiles-list").innerText = "No Client Profiles found";
            }
            document.getElementById("client-profiles-list").innerHTML = this.getHTMLForSearchResults(clientprofiles);
    }

    getHTMLForSearchResults(searchResults) {
     console.log(searchResults , "from getHTMLForSearchResults");
            if (!searchResults || !searchResults.allClientProfilesList || searchResults.allClientProfilesList.length === 0) {
                return '<h4>No results found</h4>';
            }
            let html = "";
            for (const res of searchResults.allClientProfilesList) {
                html += `
                <tr>
                <td>
                         ${res.id}
                 </td>
                 <td>
                         ${res.name}
                  </td>
                  <td>
                         ${res.phone}
                   </td>
                    <td>
                         ${res.address}
                  </td>
                  <td>
                          ${res.notes}
                 </td>
                  <td>
                          ${res.pets}
                 </td>
                </tr>`;
            }
            return html;
        }


    async addName(){
        const name = this.dataStore.get("name");
        if (name == null) {
            document.getElementById("name").innerText = "Name can not be null";
        }
        document.getElementById("name").innerText = name;
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
    const viewAllClientProfiles = new ViewAllClientProfiles();
    viewAllClientProfiles.mount();
};

window.addEventListener('DOMContentLoaded', main);