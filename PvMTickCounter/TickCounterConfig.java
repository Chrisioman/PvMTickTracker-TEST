package PvMTickCounter;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("PvMTickCounter")
public interface TickCounterConfig extends Config {

        @ConfigItem(
                keyName = "showDamage",
                name = "Show damage",
                description = "Show Individual Damage"
        )
        default boolean showDamage()
        {
                return true;
        }

        @ConfigItem(
                keyName = "damageTitleColor",
                name = "Damage Title Color",
                description = "Damage Title Color"
        )
        default Color damageTitleColor(){ return Color.WHITE; }

        @ConfigItem(
                keyName = "damageTextColor",
                name = "Damage Text Color",
                description = "Damage Text Color"
        )
        default Color damageTextColor(){ return Color.WHITE; }

        @ConfigItem(
                keyName = "showMaxHits",
                name = "Show Max Hits",
                description = "Counts Max Hits"
        )
        default boolean showMaxHits()
        {
                return true;
        }

        @ConfigItem(
                keyName = "MHTitleColor",
                name = "Damage Title Color",
                description = "Damage Title Color"
        )
        default Color MHTitleColor(){ return Color.WHITE; }

        @ConfigItem(
                keyName = "MHTextColor",
                name = "MH Text Color",
                description = "MH Text Color"
        )
        default Color MHTextColor(){ return Color.WHITE; }

@ConfigItem(
        keyName = "resetInstance",
        name = "Reset on new instances",
        description = "",
        position = 1
)
default boolean instance()
        {
        return true;
        }
@Alpha
@ConfigItem(
        keyName = "selfColor",
        name = "Your color",
        description = "",
        position = 3
)
default Color selfColor()
        {
        return Color.green;
        }
@ConfigItem(
        keyName = "totalEnabled",
        name = "Show total ticks",
        description = "",
        position = 5
)
default boolean totalEnabled()
        {
        return true;
        }
@Alpha
@ConfigItem(
        keyName = "totalColor",
        name = "Total color",
        description = "",
        position = 6
)
default Color totalColor()
        {
        return Color.RED;
        }
@Alpha
@ConfigItem(
        keyName = "otherColor",
        name = "Other players color",
        description = "",
        position = 4
)
default Color otherColor()
        {
        return Color.white;
        }
@Alpha
@ConfigItem(
        keyName = "titleColor",
        name = "Title color",
        description = "",
        position = 2
)
default Color titleColor()
        {
        return Color.white;
        }

        }
