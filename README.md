# Java Programming Chatbot API

This is a basic API for a Java programming helper chatbot. It provides answers to Java-related questions, tailored to the user's expertise level.

## Base URL
`http://localhost:8080` (replace with your host/port)

## Endpoint

```
GET /api/chat
```
### Query Parameters

| Name           | Type   | Required | Description                                                                 |
|----------------|--------|----------|-----------------------------------------------------------------------------|
| `question`     | String | Yes      | The programming-related question to ask the chatbot.                        |
| `expertiseLevel` | String | No       | The user's expertise level. Can be `beginner`, `intermediate`, or `advanced`. Defaults to `intermediate`. |

## Example Request

```
GET /api/chat?question=How%20to%20reverse%20a%20String%3F&expertiseLevel=beginner
```

**Example Response:**
`````
Reversing a String in Java means creating a new String that has the characters in the opposite order.

Here is a simple way to do it using a `for` loop:

```java
public class ReverseStringExample {
    public static void main(String[] args) {
        String original = "hello";
        String reversed = "";

        // Loop through the original string from the end to the beginning
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);  // Add each character to reversed
        }

        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);
    }
}
```

**Explanation:**

- `original.length() - 1` gives the index of the last character.
- The loop goes backward from the last character to the first.
- `charAt(i)` gets the character at position `i`.
- We add each character to the `reversed` string.

When you run this, it will print:

```
Original: hello
Reversed: olleh
```

---

**Alternative (using StringBuilder):**

Java also has a handy class called `StringBuilder` that has a built-in method to reverse strings:

```java
public class ReverseStringExample {
    public static void main(String[] args) {
        String original = "hello";
        String reversed = new StringBuilder(original).reverse().toString();

        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);
    }
}
```

This is shorter and more efficient.

Let me know if you want me to explain how `StringBuilder` works!
`````
