PrinceOfBros
============


## Plateforme cible
La cible du programme est Windows, certains éléments de la librairie Slick dépendent de la plateforme. Le projet ayant été configuré pour Windows, il sera plus simple de développer également sous Windows.

Bien entendu il est possible de changer la configuration de la librairie, mais pour se simplifier la tâches générer un seul projet pour Windows est plus simple. De plus, sauf erreur, tout le monde utilise Windows dans le groupe.

## Requis pour travailler
Java 1.7 ou plus récent (le projet est en 1.7 pour l'instant)

Si vous avez une version plus ancienne de Java, il y aura un soucis avec les instanciation génériques (new LinkedList<>() est utilisé au lieu de new LinkedList&#60;Object&#62;()).

## Mise en place du projet
Voilà la marche à suivre pour importer le projet sur Eclipse.

Je me doute que tout le monde sait déjà faire tout ça les yeux fermés, sans les mains et l'ordinateur éteint. Néanmoins j'écris la démarche que j'ai effectuée pour tester le bon fonctionnement de la configuration du projet sur une seconde machine (non configurée), vu que tout a fonctionné du premier coup.

1. Cloner le repository Github sur votre machine. Soit en lignes de commande, soit avec le logiciel de Github : https://help.github.com/articles/set-up-git (pour windows : https://windows.github.com/ ).

2. Utiliser Eclipse (https://www.eclipse.org/downloads/ ). La version standard (4.3.2) convient parfaitement, elle intègre tout ce qu'il faut pour gérer directement Git.

3. Une fois dans Eclipse :
   - Aller dans "Window" -> "Open perspectiv" -> "others" et choisir "Git"
   - Une fois ouverte, dans la partie "Git Repositories", cliquer sur l'icône Git avec le '+' (add existing repository...) 
   - Indiquer le dossier dans lequel se trouve le repository et cocher "PrinceOfBros", puis cliquer sur "Finish".
   - Revenir sur la perspective "Java" et clique droit dans "Package Explorer", puis "Import..."
   - Dérouler "Git" et choisir "Projects from Git", puis "Next", sélectionner "Existing local repository" et à nouveau "Next". Choisir "PrinceOfBros, "Next" et continuer jusqu'à ce que l'import soit fait.
   - Il ne reste qu'à aller dans "Window" -> "Show view" -> "others". Développer Git et sélectionner "Git Staging".
   
4. Le projet est déjà configuré (encodage, entêtes automatiques et formatage du code).
5. Pour lancer le programme, utiliser "Launcher.java" dans le paquetage par défaut.

### Pour mettre à jour depuis Eclipse (pull)
1. Clique droit sur le projet (Package Explorer) et "Team" -> "Pull"

### Pour commit  depuis Eclipse (push)
1. Dans "Git Staging" (voir 2.f), glisser les fichiers à commit (de "Unstaged Changes" à "Staged Changes")
2. Ecrire un message intelligent
3. Cliquer sur "Commit and Push" ou "Commit" pour ne pas envoyer directement sur le serveur (Il faudra "Push" plus tard en allant au même endroit que pour pull, mais cliquer sur "Push to upstream...")
