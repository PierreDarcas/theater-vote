# API Appli Théâtre
Cette application doit permettre au spectateur d’un théâtre de poster des critiques sur les pièces jouées dans ce théâtre. 
Les informations concernant les pièces, ainsi que les critiques laissé par un spectateur doivent être visible de tous, à l'inverse des informations personelles des spectateurs qui doivent être confidentielles.
Les fonctionnalités de l’application sont les suivantes :
* Créer/Modifier/Supprimer un compte spectateur ;
* Créer/Modifier/Supprimer une pièce ;
* Créer/modifier/supprimer une critique ;
* Lister toutes les critiques poster par un spectateur ;
* Lister toutes les critiques à propos d’une pièce.
# Installation de l’API :
* ```docker pull pdarcas/theater-app:latest ```
* ```docker run -it -p 8282:8282 pdarcas/theater-app:latest```
## Test:
http://localhost:8282/toto

Un fichier insomnia est présent dans ./insomnia/insomnia, il contient des requêtes pour tester les endpoints. certaines requête integrent une authentification token-bearer, la première requête et une demande de token Auht0.

# Surface d'attaque

[![](https://mermaid.ink/img/eyJjb2RlIjoiZ3JhcGggVERcbiAgQVtPU10gLS0-QltEb2NrZXJdXG4gIEIgLS0-IENbVG9tY2F0XVxuICBDIC0tPiBFW1NwcmluZ0Jvb3RdXG4gIEIgLS4gQkREIEgyIC4tRVxuICBFIC0tPiBGW0FQSS10aGVhdGVyVm90ZV1cbiAgRiAtLT4gSFtcIi92aWV3ZXJcIl1cbiAgRiAtLT4gSVtcIi9yZXZpZXdcIl1cbiAgRiAtLT4gSltcIi9wbGF5XCJdXG5cblx0XHQiLCJtZXJtYWlkIjp7fSwidXBkYXRlRWRpdG9yIjpmYWxzZX0)](https://mermaid-js.github.io/mermaid-live-editor/#/edit/eyJjb2RlIjoiZ3JhcGggVERcbiAgQVtPU10gLS0-QltEb2NrZXJdXG4gIEIgLS0-IENbVG9tY2F0XVxuICBDIC0tPiBFW1NwcmluZ0Jvb3RdXG4gIEIgLS4gQkREIEgyIC4tRVxuICBFIC0tPiBGW0FQSS10aGVhdGVyVm90ZV1cbiAgRiAtLT4gSFtcIi92aWV3ZXJcIl1cbiAgRiAtLT4gSVtcIi9yZXZpZXdcIl1cbiAgRiAtLT4gSltcIi9wbGF5XCJdXG5cblx0XHQiLCJtZXJtYWlkIjp7fSwidXBkYXRlRWRpdG9yIjpmYWxzZX0)

# Endpoints
* http://localhost:8282/play => Public
* http://localhost:8282/play/id => Public
* http://localhost:8282/play/create => Necessite un token
* http://localhost:8282/play/delete/* => Necessite un token
* http://localhost:8282/play/update/* => Necessite un token
* http://localhost:8282/review => Public
* http://localhost:8282/review/id => Public
* http://localhost:8282/review/viewer => Public
* http://localhost:8282/review/play => Public
* http://localhost:8282/review/create => Public
* http://localhost:8282/review/update/* => Necessite un token
* http://localhost:8282/review/delete/* => Necessite un token
* http://localhost:8282/viewer/id => Necessite un token
* http://localhost:8282/viewer/create => Public
* http://localhost:8282/viewer/delete/* => Necessite un token
* http://localhost:8282/viewer/update/* => Necessite un token

# Spring security

Pour répondre à l'objectif que je m'étais fixé de limiter l'accés de certaines parties de l'application j'ai implémenté Auth0 dans le package security de mon application.
L'application demande de fournir un token pour exploter tout les endpoints listé ci dessus.

# ORM JPA

L’accès à la base de données se fait via L’ORM JPA. L’utilisation d’un ORM permet de se sécuriser le code face aux injections SQL, cela dit certaines failles peuvent encore exister.

# Image docker jib

J’ai utilisé le plugin jib pour créer plus facilement mon image docker à partir de mon app spring. Jib crée des images docker minimal sans utiliser de docker-file, ce qui renforce la sécurité de l’image.

# CSRF disable

Le module csrf de spring sécurité a été désactivé. Dans le cadre d’une API destiné à communiqué avec d’autres API il n’est d’une utilité critique, et son activation est gênante puisque seul les requête POST et GET sont authorisées.

# Objectifs de sécurité


1. Confidentialité
    * /viewer => La table viewer contient des emails et des mots de passes 5/5
    * /review => Les critiques sont publiques 0/5
    * /play => Les informations relatives aux pièces sont publiques 0/5

2. Intégrité
    * /viewer => Il est toujours possible de regénérer un mot de passe en cas de perte d'intégrité de ce dernier 2/5
    * /review => Les critiques pourrait être falsifié dans le but de nuire à la réputation de l'établissement 3/5
    * /play => Les informations relatives aux pièces sont généralement disponibles à d'autres endroits  1/5
3. Disponibilité
    * /viewer => Une perte de disponibilité d'un espace spectateur empêche d'émettre une critique, mais pas de visualiser les pièces  2/5
    * /review => Si un spectateur ne peut pas émettre une critique lorsqu'il le souhaite, il pourrait se décourager 3/5
    * /play => Si un spectateur n'a pas accès aux informations sur les pièces, alors il ne viendra surement pas en voir une  4/5

4. Traçabilité
    * /viewer => Un spectateur peut vouloir consulter son historique 2/5
    * /review => On doit pouvoir identifier les critiques déplacé et abusives 5/5
    * /play => On peut vouloir consulter l'historique du théâtre 1/5
# Rappel de la consigne 

Projet Individuel
Deadline : 10/02/21

---

Ecrivez un projet sur lequel vous avez carte blanche (carte blanche sur le langage et les fonctionnalités du projet).

Pour que le projet soit reçevable, ses pré-requis sont :

- Le projet doit pouvoir être lancé avec une commande docker (et donc doit avoir un Dockerfile).
  - Si la commande Docker ne fonctionne pas, la note sera sévère parce que je vais pas débugger le code de 30 personnes.
  - La commande doit apparaitre dans /README.md

- Le projet doit faire apparaitre un dossier /tests qui contiendra les tests unitaires qui permettraient de trouver les vulnérabilités dans votre code (vous avez carte blanche sur votre framework de test)
  - Par exemple : Un test d'injection SQL sur un paramètre GET

- Le projet doit faire un apparaitre un dossier /exploit qui contiendra les scripts qui permettront d'exploiter les vulnérabilités (vous avez carte blanche sur le langage du script, il faut que le script soit concis)

- Le projet doit être un dépôt github publique, pour que je puisse y jeter des coups d'oeil et pour les corrections

- Le code doit être lisible et donc correctement commenté pour quelqu'un qui n'est pas dev (genre moi) mais qui sait lire de code (pas de "i++; // on incrémente i"). Si le code n'est pas propre et me fait perdre du temps quand je le lis, il y aura des malus.

- Le fichier README.md doit faire apparaitre les objectifs de sécurité de l'application :
    - Ses objectif en Confidentialité, Intégrité et Disponibilité (note /5)

- Le fichier README.md doit faire apparaitre un graph mermaidjs avec la surface d'attaque

- Un fichier Excel avec la même analyse de sécurité que le premier TP doit aussi apparaitre.

---

Ce TP est le projet du cours et donnera votre note de ce cours !

# Jalons 

- [x] Premier Jalon - Donnez moi juste l'url du projet
Envoyez moi pour le 14/12/20 vos repos github à l'adresse pro.loris.sierra@gmail.com.
Si vous souhaitez garder votre repo privé, ajoutez mon compte github loris-intergalactique à votre ACL.
Ce sont les repos github qui contiendront votre code et sur lesquels je vais m'appuyer. Un fichier README.md doit apparaître avec vos premières idées d'application.
- [x] Deuxième jalon - Faut que ça marche !
Au 3/01/21, vos projets doivent fonctionner (les projets, avec ou sans vulnérabilité).
Voici les exigences de ce jalon :
- Je veux pouvoir lancer une image Docker et que tout fonctionne correctement. 
- La commande Docker à faire doit apparaître dans le README.
- Expliquez dans le README ce que fait votre travail :)
- La surface d'attaque doit être faite dans un fichier mermaidjs qui apparait dans le repo.
- Vos objectifs de sécurité (C, I, D, T) doivent apparaitre : Une note/5 et une explication
- [X] Troisième Jalon - Presque fin de projet
- Soit vous avez fait un projet qui contient des vulnérabilités :
  - Pour chaque vulnérabilité, vous la décrivez :
    - Vous décrivez en quoi votre code est vulnérable ;
    - Vous définissez le(s) mécanisme(s) ET le(s) principe(s) de sécurité que vous auriez du appliquer pour éviter cette vulnérabilité ;
    - Vous indiquez la bibliothèque et sa ou ses fonctions permettant d'appliquer le mécanisme de sécurité en question ;
    - Décrivez les tests que vous feriez pour vérifier que cet endpoint (cette page web) n'est pas vulnérable (Les tests vont vous permettre de renforcer l'implémentation des principes de dev sécurisés)
   - (En bonus) Quel impact aurait eu cette vulnérabilité sur votre métier (Injection SQL => Fuite de données => Incident diplomatique => vous fermez votre business)

---------------------------------------------------------------------

- Soit vous avez fait un projet qui n'est théoriquement pas vulnérable :
  - Pour chaque fonctionnalité de sécurité que vous ajoutez (ORM par exemple) :
    - Vous décrivez de quoi la fonctionnalité protège votre code
    - Vous décrivez comment la fonctionnalité protège votre code
    - Vous définissez le(s) mécanisme(s) ET le(s) principe(s) de sécurité que votre code implique ;
    - Décrivez les tests que vous feriez pour vérifier que cet endpoint (cette page web) n'est pas vulnérable (Les tests vont vous permettre de renforcer l'implémentation des principes de dev sécurisés)
   - (En bonus) Quel impact aurait eu cette vulnérabilité sur votre métier (Injection SQL => Fuite de données => Incident diplomatique => vous fermez votre business)

---------------------------------------------------------------------

- Si vous pensez qu'un endroit est vulnérable, mais vous ne savez pas l'expliquer :
  - Vous mettez quand même un commentaire pour dire que vous le pensez
  - Et si vous avez plus d'infos de pourquoi vous comprenez pas, vous le mettez
  - Si vous désactivez une fonction de sécurité : Par exemple, pour ".csrf().disable()" : Vous décrivez de quoi protégait cette option, pourquoi vous la désactivez et en quoi votre méthode est meilleure (tant que c'est équivalent en sécu. La raison peut être niveau perf ou compatibilité, ou n'importe quoi d'autre)

