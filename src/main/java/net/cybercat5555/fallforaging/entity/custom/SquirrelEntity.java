package net.cybercat5555.fallforaging.entity.custom;

import net.cybercat5555.fallforaging.entity.ModEntities;
import net.cybercat5555.fallforaging.item.ModItems;
import net.cybercat5555.fallforaging.util.ModTags;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SquirrelEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public SquirrelEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.updateFleeing();
    }

    boolean isTrusting() {
        return (Boolean)this.dataTracker.get(TRUSTING);
    }

    private void setTrusting(boolean trusting) {
        this.dataTracker.set(TRUSTING, trusting);
        this.updateFleeing();
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Trusting", this.isTrusting());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setTrusting(nbt.getBoolean("Trusting"));
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TRUSTING, false);
    }

   @Override
   protected void initGoals(){
       this.goalSelector.add(0, new SwimGoal(this));

       this.goalSelector.add(1, new AnimalMateGoal(this, 1.15D));
       this.goalSelector.add(1, new EscapeDangerGoal(this, 2.5));
       this.goalSelector.add(2, new TemptGoal(this, 1.25D, (stack) -> {
           return stack.isIn(ModTags.ACORNS);
       }, false));
      // this.goalSelector.add(2); new FleeEntityGoal<>(entity:this, speed:2D,)

       this.goalSelector.add(3, new FollowParentGoal(this, 1.1D));

       this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
       this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
       this.goalSelector.add(6, new LookAroundGoal(this));
   }

   public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes().add()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18 )
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,  1.2)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,  10);
   }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    private boolean isValidItem(ItemStack stack) {
        return stack.isIn(ModTags.ACORNS);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ModTags.ACORNS);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.SQUIRREL.create(world);
    }
}
