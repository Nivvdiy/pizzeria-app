const api = 'http://localhost:3000/commandes'

export class CommandeService {
    constructor($http, $localStorage, PizzaService) {
        this.$http = $http;
        this.totalCommande;
        this.$localStorage = $localStorage;
        this.commande = [];
        this.PizzaService = PizzaService;
    }

    majCommande(listeProduit, total) {

        this.$localStorage.commandeEnCours = {};

        this.$localStorage.commandeEnCours.listeProduit = listeProduit;
        this.$localStorage.commandeEnCours.total = total;
    }

    majQuantiteCache(produit) {

        let tab = this.$localStorage.commandeEnCours.listeProduit;
        let elem = _.find(tab, p => p.idProduit === produit.id && p.type === produit.type);

        elem.quantite = produit.quantite;
    }

    supprimerProduitDuCache(produit) {

        let panier = this.$localStorage.jsonPanier;
        let commande = this.$localStorage.commandeEnCours.listeProduit;
        _.remove(panier, e => e.idProduit === produit.id && e.type === produit.type);
    }

    commandeTMP() {
        // this.$localStorage.commandeEnCours = {};
        // this.$localStorage.commandeEnCours.total = 42.50;
        // this.$localStorage.commandeEnCours.listeProduit = [{ "type": "pizza", "idProduit": 1, "quantite": 12 },
        // { "type": "pizza", "idProduit": 2, "quantite": 1 },
        // { "type": "pizza", "idProduit": 3, "quantite": 11 }];
        return this.$localStorage.commandeEnCours;
    }


    getCommandesByUserId(id) {
        return this.$http.get(`${api}/${id}`)
            .then(response => response.data);
    }

    envoyeCommandeCache(commandeATraiter) {
        commandeATraiter.forEach(function (element, index) {
            if (commandeATraiter[index].type === "pizza") {
                element.idClient = commandeATraiter.idClient;
                element.total = commandeATraiter.total;
                this.commande.push(element);
            }
        }, this);
        console.log(this.commande);
        return this.$http.post(api, this.commande)
    }

    getCommandeById(id) {
        return this.$http.get(`${api}/commande/${id}`)
            .then(response => response.data);
    }
}
