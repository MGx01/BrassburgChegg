package io.github.mgx01.brassburgchegg.impl.data.pattern.params;

public class MobParams {
    public class Frog {
        public int special_range;
        public int pull_distance;
    }

    public class Creeper {
        public boolean friendly_fire;
    }

    public class Pig {
        public int on_spawn;
        public int on_death;
    }

    public class Rabbit {
        public int draw_on_vault;
    }

    public class PufferFish {
        public boolean friendly_fire;
    }

    public class IronGolem {
        public int cleave_width;
        public boolean friendly_fire;
    }

    public class Phantom {
        public boolean can_dash;
    }

    public class Enderman{
        public String directions;
        public boolean infinite_range;
        public String excluded_target;
        public boolean movement_costs_special_mana;
    }
    
    public class Slime{
        public boolean block_dash_action;
    }
    
    public class ShulkerBox{
        public boolean move_on_hit;
        public boolean is_projectile;
    }
    
    public class Parrot{
        public String proximity;
        public int special_range;
    }
    
    public class Cat{
        public int mana_generation_per_turn;
        public boolean can_dash;
    }
    
    public class Sniffer{
        public int draw_from_enemy_on_spawn;
        public int discard_from_self_at_death;
    }
    
    public class Turtle{
        public boolean charge_movement;
        public boolean can_affects_allies;
        public boolean can_affects_self;
        public boolean block_dash_action;
    }
    
    public class SnowGolem{
        public String effect;
        public boolean is_projectile;
    }

    public class Wither{
        public boolean spawn_destruction_aura;
        public boolean meele_destruction_aura;
        public boolean is_projectile;
        public boolean has_splash_damage;
        public boolean friendly_fire;
    }
}
