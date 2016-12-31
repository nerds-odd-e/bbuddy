export function Inject(...dependencies) {
    return (target, key, descriptor) => {
        if (descriptor) {
            const fn = descriptor.value
            fn.$inject = dependencies
        } else {
            target.$inject = dependencies
        }
    }
}