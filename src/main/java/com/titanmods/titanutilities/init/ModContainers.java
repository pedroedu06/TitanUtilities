package com.titanmods.titanutilities.init;

import com.titanmods.titanutilities.TitanUtilities;
import com.titanmods.titanutilities.container.TitanCrafterContainer;
import com.titanmods.titanutilities.container.TitanFactoryContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, TitanUtilities.MOD_ID);

    public static final RegistryObject<ContainerType<TitanFactoryContainer>> TITAN_FACTORY =
            CONTAINERS.register("titan_factory", () -> IForgeContainerType.create(TitanFactoryContainer::new));

    public static final RegistryObject<ContainerType<TitanCrafterContainer>> TITAN_CRAFTER =
            CONTAINERS.register("titan_crafter", () -> IForgeContainerType.create(TitanCrafterContainer::new));
}
