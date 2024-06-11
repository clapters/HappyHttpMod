# Coding Standards for Happy HTTP Minecraft mod

## General Coding Standards

### Naming Conventions
- **Classes**: Use `PascalCase` (e.g., `MyAwesomeMod`).
- **Methods**: Use `camelCase` (e.g., `initializeMod`).
- **Variables**: Use `camelCase` (e.g., `playerHealth`).
- **Constants**: Use `UPPER_CASE_WITH_UNDERSCORES` (e.g., `MAX_HEALTH`).
- **Packages**: Use `lowercase.with.dots` (e.g., `com.example.myawesomemod`).

### File Organization
- Group related classes and interfaces into packages.
- Follow a logical structure, e.g., `com.example.myawesomemod.blocks`, `com.example.myawesomemod.items`.

### Code Layout
- Use 4 spaces per indentation level.
- Limit lines to 80 characters.
- Leave a blank line between methods to improve readability.
- Use a single blank line to separate logical sections of your code.

## Minecraft Modding Specific Standards

### Mod Structure
- **Main Mod Class**: Have a main class annotated with `@Mod`.
- **Proxy Classes**: Use proxies (`CommonProxy` and `ClientProxy`) to handle client-specific and server-specific code.

### Resource Organization
- Place textures in `src/main/resources/assets/modid/textures`.
- Place models in `src/main/resources/assets/modid/models`.
- Place localization files in `src/main/resources/assets/modid/lang`.

## Java Specific Standards

### Documentation
- Use Javadoc for all public classes and methods.
- Provide descriptions for parameters and return values.

```java
/**
 * Calculates the player's health.
 *
 * @param player The player entity.
 * @return The calculated health value.
 */
public int calculateHealth(PlayerEntity player) {
    // Method implementation
}
```

### Exception Handling
Avoid using exceptions for control flow.
Catch specific exceptions instead of generic ones.
Log exceptions with a meaningful message

```java
try {
    // Code that might throw an exception
} catch (SpecificException e) {
    logger.error("A specific error occurred: ", e);
}
```

### Avoid Magic Numbers
Replace magic numbers with named constants.

// Bad
```java
int maxHealth = 100;
```

// Good
```java
private static final int MAX_HEALTH = 100;
int maxHealth = MAX_HEALTH;
```

### Best Practices for Minecraft Modding
Events
Use Forge events to interact with Minecraft's lifecycle and other mods.
Register event handlers in your main mod class or a dedicated event handler class.

```java
@Mod.EventBusSubscriber(modid = MyMod.MODID)
public class MyModEventHandler {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        // Event handling code
    }
}
```

### Configuration
Use Forge’s configuration system to make your mod configurable.
Place configuration files in src/main/resources/config.

```java
@Config(modid = MyMod.MODID)
public class MyModConfig {
    public static ConfigValue<Integer> exampleValue;
}
```


### Networking
Use simple networking for server-client communication.
Define your packet handling logic clearly.

```java
public class MyModPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(MyMod.MODID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );
}
```

## Version Control Best Practices

### Commit Messages
Write clear and concise commit messages.
Use the imperative mood in the subject line (e.g., "Fix bug in health calculation").
Structure your commits logically, making sure each commit is self-contained.

### Branching
Use branches for new features and bug fixes.
Follow a naming convention for branches (e.g., feature/feature-name, bugfix/issue-number).

### Example Configuration for Checkstyle
To enforce some of these standards programmatically, you can use Checkstyle with a custom configuration. Here’s an example checkstyle.xml configuration file:

```
<!DOCTYPE module PUBLIC "-//Checkstyle//DTD Checkstyle Configuration 1.3//EN"
    "https://checkstyle.sourceforge.io/dtds/configuration_1_3.dtd">
<module name="Checker">
    <module name="TreeWalker">
        <module name="JavadocMethod">
            <property name="scope" value="public"/>
            <property name="allowMissingParamTags" value="false"/>
            <property name="allowMissingReturnTag" value="false"/>
        </module>
        <module name="MagicNumber">
            <property name="ignoreNumbers" value="0,1,-1"/>
        </module>
        <module name="AvoidStarImport"/>
        <module name="LineLength">
            <property name="max" value="100"/>
        </module>
        <module name="FileTabCharacter">
            <property name="eachLine" value="true"/>
        </module>
    </module>
</module>
```

By adhering to these coding standards and best practices, we can ensure that our Minecraft mod code is clean, maintainable, and easier for collaborators to understand.
