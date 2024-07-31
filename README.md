# McForgeKids
Minecraft mod for kids to tinker with.

## Initial Setup
1. Install: IntelliJ IDEA Community Edition
   * https://www.jetbrains.com/idea/download/?section=windows
   * Scroll down to find the link for the free community edition.
1. Run IntelliJ IDEA.
1. Clone this project from GitHub.
   * From the welcome screen, select "Get From VCS".
   * Git URL: https://github.com/portablestew/McForgeKids.git
   * Local directory: any location you like. Recommended: `[user]\Documents\github\McForgeKids`
1. Find the Gradle icon along the edge of the IntelliJ window. It looks like a small elephant. Open it.
   * Wait for Gradle to finish generating the project. The project status is on the bottom right of the IntelliJ window.
1. In the now-populated Gradle menu, expand: `McForgeKids -> Tasks -> forgegradle runs`
   * Double-click `genIntellijRuns` to run it. Wait for it to complete.
1. From the same Gradle menu, run `runClient`. This will build and launch the game!
1. Play the modded Minecraft. 
   * Look at the "Mods" menu and find McForgeKids. 
   * Create a world and try out modded items.
1. Exit the Minecraft client.
1. Close the Gradle menu. To launch Minecraft next time, press Shift+F9.
1. Install optional plugins. From the top left menu, select `File -> Settings -> Plugins` and search the marketplace.
   * Minecraft Development -- IDE enhancements specific to Minecraft/Forge development. 
   * Amazon Q -- Generative AI that assists with writing code.
1. Open the "Project" menu, which looks like a folder. Familiarize yourself with the files. 
   * Especially look at `McForgeKids -> src -> main`
     * `java` -- The code files. This is where modded objects are registered, and their behaviors defined.
     * `resources` -- Data files, including textures, the map of entity ids to names, and others.

## Spawn Storm Arrows
These are special arrows that spawn a "storm" of projectiles where they land.
 * hailstorm_arrow: spawns snowballs
 * firestorm_arrow: spawns small fireballs

Additional spawn storm arrows are straightforward to add:
1. Create a new "storm" entity. 
   *This is a class extending BaseSpawnStormEntity. 
   * Copy HailstormEntity as an example.
   * Change the `new Snowball` to a different kind of entity.
     * To find projectile entities from vanilla Minecraft, [browse the Projectile class tree](https://nekoyue.github.io/ForgeJavaDocs-NG/javadoc/1.20.6-neoforge/net/minecraft/world/entity/projectile/Projectile.html).
   * Register it in EntityRegistry. 
1. Create a new arrow entity, which spawns the storm on impact.
   * This is a class extending BaseSpawnStormArrow.
   * Copy HailstormArrowProjectile as an example.
   * Register it in EntityRegistry.
1. Add a new texture file for the arrow.
   * Place it in `resources/assets/mcforgekids/textures`.
   * Copy an existing png file there as an example.
1. Add a renderer for the arrow entity.
   * Register another SimpleArrowRenderer in RendererRegistry.
   * Use the path to the new texture file. 
1. Create a new arrow item, which spawns the arrow entity when called for.
   * Copy HailstormArrowItem as an example.
   * Register it in ItemRegistry, including adding it to the creative tab. 
   * Allow it to be fired from a bow: `resources/data/minecraft/tags/items/arrows.json`
   * Give it a readable name: `resources/assets/mcforgekids/lang/en_us.json`
1. Launch the game and test it out!
