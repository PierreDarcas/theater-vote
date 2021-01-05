# Appli critique de théâtre
Cette application doit permettre au spectateur d’un théâtre de poster des critiques sur les pièces jouées dans ce théâtre. Les fonctionnalités de l’application sont les suivantes :
•	Créer/Modifier/Supprimer un compte spectateur ;
•	Créer/Modifier/Supprimer une pièce ;
•	Créer/modifier/supprimer une critique ;
•	Lister toutes les critiques poster par un spectateur ;
•	Lister toutes les critiques à propos d’une pièce.
Installation de l’API :
docker pull pdarcas/theater-app:latest docker run -it -p 8282:8282 pdarcas/theater-app:latest
Test:
http://localhost:8282/toto

[![](https://mermaid.ink/img/eyJjb2RlIjoiZ3JhcGggVERcbiAgQVtPU10gLS0-QltEb2NrZXJdXG4gIEIgLS0-IENbVG9tY2F0XVxuICBDIC0tPiBFW1NwcmluZ0Jvb3RdXG4gIEIgLS4gQkREIEgyIC4tRVxuICBFIC0tPiBGW0FQSS10aGVhdGVyVm90ZV1cbiAgRiAtLT4gSFtcIi92aWV3ZXJcIl1cbiAgRiAtLT4gSVtcIi9yZXZpZXdcIl1cbiAgRiAtLT4gSltcIi9wbGF5XCJdXG5cblx0XHQiLCJtZXJtYWlkIjp7fSwidXBkYXRlRWRpdG9yIjpmYWxzZX0)](https://mermaid-js.github.io/mermaid-live-editor/#/edit/eyJjb2RlIjoiZ3JhcGggVERcbiAgQVtPU10gLS0-QltEb2NrZXJdXG4gIEIgLS0-IENbVG9tY2F0XVxuICBDIC0tPiBFW1NwcmluZ0Jvb3RdXG4gIEIgLS4gQkREIEgyIC4tRVxuICBFIC0tPiBGW0FQSS10aGVhdGVyVm90ZV1cbiAgRiAtLT4gSFtcIi92aWV3ZXJcIl1cbiAgRiAtLT4gSVtcIi9yZXZpZXdcIl1cbiAgRiAtLT4gSltcIi9wbGF5XCJdXG5cblx0XHQiLCJtZXJtYWlkIjp7fSwidXBkYXRlRWRpdG9yIjpmYWxzZX0)
