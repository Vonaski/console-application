# Java Console Application

A Java console application combining two core utilities:
1. **Advanced Caesar Cipher** with brute-force decryption
2. **Arithmetic Expression Evaluator** with PEMDAS support

## Features

### Caesar Cipher
- Encrypt/decrypt text with configurable shift value
- Brute-force decryption for unknown shifts (1-33 attempts)
- Dual alphabet support: English (A-Z) and Russian (А-Я)
- File input/output operations
- Case preservation and special character handling

### Arithmetic Calculator
- Basic operations: `+`, `-`, `*`, `/`
- Parentheses for operation precedence
- Decimal number support
- Error handling for invalid expressions
- File input/output capabilities

## Installation & Usage

### Requirements
- Java 17 or later
- Maven (optional)

### Command Line Setup
```bash
# Compile all source files
javac *.java

# Run the application
java main.java.app.Main
```

## Usage Examples

### Caesar Cipher Operations

**1. English Text Encryption**
Choose operation: 1
Caesar Cipher: 1 (Encrypt)
Input method: 1 (Console)
Enter text: Hello World!
Shift value: 3
Encrypted text: Khoor Zruog!


**2. Russian Text Decryption**
Choose operation: 2 (Decrypt)
Input method: 2 (File)
File path: secret_ru.txt
Shift value: 5
Decrypted text: Привет, мир!


**3. Brute-force Decryption (Partial Output)**
Choose operation: 3 (Brute-force)
Enter text: Khoor
Brute-force attempts:
Shift 1: Jgnnq
Shift 2: Ifmmp
Shift 3: Hello ← Correct plaintext
...
Shift 26: Axeeh


**4. Negative Shift Encryption**
Enter text: ABCxyz
Shift value: -3
Encrypted text: XYZuvw


### Arithmetic Calculator

**1. Basic Operations**
Enter expression: 3 + 5 * 2
Result: 13.0


**2. Parentheses Handling**
Enter expression: (2 + 3) * (4 - 1)
Result: 15.0


**3. Decimal Division**
Enter expression: 10.5 / 2 + 3.75
Result: 8.0


**4. Complex Expression**
Enter expression: 3.5 * (4.2 - 1.8) / 2
Result: 4.2


### File Operations

**1. Encrypt File Contents**
input.txt:
This is a secret message!

Choose operation: 1
Input method: 2 (File)
File path: input.txt
Shift value: 7
Encrypted text: Aopz pz h zljyla tlzzhnl!


**2. Evaluate Expression from File**
math.txt:
(15 + 3²) / 4 # Supports exponent notation

Result: 6.0


### Error Handling Examples

**1. Invalid Shift Value**
Shift value: abc
Error: Shift must be an integer


**2. Division by Zero**
Enter expression: 5 / (3 - 3)
Error: Division by zero


**3. Mismatched Parentheses**
Enter expression: 2 * (3 + 4
Error: Mismatched parentheses


**4. Invalid Characters**
Enter text: 你好
Error: Non-alphabetic characters preserved: 你好


**5. File Not Found**
File path: missing.txt
Error: File not found: missing.txt


## Advanced Usage

**Chain Operations**  
Encrypt → Save → Decrypt from file:
Encrypt and save
java main.java.app.Main <<EOF
1
1
1
SecretData
3
y
encrypted.txt
EOF

Decrypt from file
java main.java.app.Main <<EOF
1
2
2
encrypted.txt
3
y
decrypted.txt
EOF