# McForgeKids
Minecraft mod for kids to tinker with.

## Spawn Storm Arrows
These are special arrows that spawn a "storm" of projectiles where they land.
* hailstorm_arrow: spawns snowballs
* firestorm_arrow: spawns small fireballs

Additional spawn storm arrows are straightforward to add:
1. Create a new "storm" entity. 
    * This is a class extending BaseSpawnStormEntity. 
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
