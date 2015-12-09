#include <stdio.h>
#include <string.h>

enum { BUFFERSIZE = 15 };

// Function prototypes
void fillPassword(size_t, char[]);
void showResults(char);
void showMenu(void);

// Define a variable to hold a password
// and the copy
char password[BUFFERSIZE] = "";
char cpassword[BUFFERSIZE] = "";

int main(void)
{
	// Welcome the User
	printf_s("Welcome to the C Array Program!\n");

	// Variables
	char cont = 'y'; // To continue with loop
	int cVar = 0; // process variable

	// Display menu and Get Selection
	while (cont != 'E' && cont != 'e') {
		// Display the Menu
		showMenu();

		// Get the user selection
		scanf_s("%c", &cont, 1);
		//No user input accepted; just clears newline char
		getchar();

		// Display the menu response
		showResults(cont);
	}
	// Call the Copy routine	
	fillPassword(sizeof(password), password);

	// Display variable values
	printf_s("password is %s\n", password);
	printf_s("cVar is %d\n", cVar);
	printf_s("Length of password is %d\n", sizeof(password) - 1);

	// Copy password 	
	memcpy_s(cpassword, sizeof(cpassword), password, sizeof(password));

	// Pause before exiting
	printf_s("Press enter to confirm your exit!");
	getchar();
	return 0;
}

// Make a String of '1's
void fillPassword(size_t n, char dest[]) {
	// Should be n-1
	for (size_t j = 0; j < n - 1; j++) {
		dest[j] = '1';
	}
	// Add null terminator for string
	dest[n] = '\0';
}

/* Display the Results*/
void showResults(char value) {
	switch (value) {
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
	printf_s("Enter a selection from the following menu.\n");
	printf_s("B. Baseball season.\n");
	printf_s("F. Football season.\n");
	printf_s("S. Soccer season.\n");
	printf_s("E. Exit the system.\n");
}
