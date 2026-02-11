# SOLID Principles

## Single Responsibility Principle (SRP)

A class should have one, and only one, reason to change.

### Example
```python
# Bad
class User:
    def __init__(self, name):
        self.name = name
    
    def save_to_db(self):
        pass
    
    def send_email(self):
        pass

# Good
class User:
    def __init__(self, name):
        self.name = name

class UserRepository:
    def save(self, user):
        pass

class EmailService:
    def send(self, user):
        pass
```

## Open/Closed Principle (OCP)

Software entities should be open for extension, but closed for modification.

## Liskov Substitution Principle (LSP)

Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.

## Interface Segregation Principle (ISP)

A client should never be forced to depend on interfaces it does not use.

## Dependency Inversion Principle (DIP)

Depend on abstractions, not on concrete implementations.
