#include <unistd.h>
   #include <stdio.h>
   #include <errno.h>
#include <stdlib.h> //obligatoire
#include<string.h>






int main(int argc, char *argv[])
{
    char cwd[1024];
    char * cwds;
       if (getcwd(cwd, sizeof(cwd)) != NULL){
         
           fprintf(stdout, "emplacement du repertoire courant: %s\n", cwd);
           
             fprintf(stdout,"%s\n",cwd);
    printf("***********************************************************************\n");
    printf("***********************************************************************\n");           
    printf("***********************************************************************\n");
    printf("      Bienvenu dans CessCrea2.1 Logiciel de traitement\n");
    printf("        des Cessions de creances devellopee et fourni pour \n");
    printf("         pour le compte de la TGR ministére des finances\n");
    printf(" Autheur :   Belmamoun Adnane-----> Belm's software\n");          
    printf("**********************************************************************\n");
    printf("**********************************************************************\n");
    printf("**********************************************************************\n");
  printf("Appuyez sur Entrer pour lancer CessCrea2.1  ...\n");
  getchar();
  system("start CessCrea2.1.jar"); 
  
  }
       else
         {  perror("getcwd() error");}
         
       return 0;

}
