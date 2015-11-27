#include <stdio.h>
#include <string.h>

// Function prototypes
void fillPassword(size_t , char[]);
void showResults(char);
// should have void listed
void showMenu(void);

// Define a variable to hold a password
// and the copy
char password[15] = "";
char cpassword[15] = "";

int main(void)
{ 
	// Welcome the User
	printf("Welcome to the C Array Program!\n");

	// Variables
	char cont = 'y'; // To continue with loop
	int cVar = 0; // process variable
        char confirm = 'y';

	// Display menu and Get Selection
	while (cont != 'E' && cont != 'e') {
		// Display the Menu
		showMenu();
		
		// Get the user selection
		cont = getchar();
                //Eliminate the "new line" char used in error by next getchar()
                getchar();
		
		// Display the menu response
		showResults(cont);
	}
	// Call the Copy routine	
	fillPassword(sizeof(password),password);
	
	// Display variable values
	printf("password is %s\n", password);
	printf("cVar is %d\n", cVar);

	// Copy password 	
	memcpy(cpassword, password,sizeof(password));	
	
	// Pause before exiting
	printf("Press enter to confirm your exit!");
        confirm = getchar();
	return 0;
}

// Make a String of '1's
void fillPassword(size_t n, char dest[]) {
	// Should be n-1
	 for (size_t j = 0; j < n-1; j++) {	
		dest[j] = '1';
	}
	// Add null terminator for string
	dest[n] = '\0';
}

/* Display the Results*/
void showResults(char value) {
	printf("cont/value is %s\n", value);
	printf("--------------\n");
	switch (value){
	case 'F':
	case 'f':
		printf("Welcome to the Football season!\n");
		break;
	case 'S':		
	case 's':
		printf("Welcome to the Soccer season!\n");
		break;
	case 'B':		
	case 'b':
		printf("Welcome to the Baseball season!\n");
		break;			
	case 'E':		
	case 'e':
		printf("Exiting the Menu system!\n");
		break;
	default:
		printf("Please enter a valid selection\n");
                break;
	}
	
}

/* Display the Menu*/
void showMenu(void) {
	printf("Enter a selection from the following menu.\n");
	printf("B. Baseball season.\n");
	printf("F. Football season.\n");
	printf("S. Soccer season.\n");
	printf("E. Exit the system.\n");
}
